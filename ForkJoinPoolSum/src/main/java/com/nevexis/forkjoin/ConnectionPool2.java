package com.nevexis.forkjoin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool2 {
	private static ReentrantLock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	private static List<Connection> connections = new ArrayList<>();
	private String url;
	private String user;
	private String password;
	private static int SIZE = 2;

	public ConnectionPool2(String url, String user, String password, int size) throws SQLException {
		this.url = url;
		this.user = user;
		this.password = password;
		ConnectionPool2.SIZE = size;
		init();
	}

	private void init() throws SQLException {
		connections = new ArrayList<>(SIZE);
		lock.lock();
		for (int i = 0; i < SIZE; i++) {
			connections.add(DriverManager.getConnection(url, user, password));
		}
		lock.unlock();
	}

	public Connection getConnection() throws InterruptedException {

		try {
			lock.lock();
			while (connections.isEmpty()) {
				
				condition.await();
			}
			return connections.remove(connections.size() - 1);

		} finally {
			lock.unlock();
		}

	}

	public void releaseConnection(Connection connection) {
		try {
			lock.lock();
			connections.add(connection);
			System.out.println("Released");
			condition.signalAll();
		} finally {
			lock.unlock();
		}

	}

	public static void main(String[] args) throws SQLException, InterruptedException {
		ConnectionPool2 connectionPool = new ConnectionPool2("jdbc:mysql://localhost:3306/personschema?useSSL=false",
				"root", "root", 5);
		Connection firstConnection = connectionPool.getConnection();
		System.out.println(firstConnection);
		
		Thread t1 = new Thread(() -> {
			try {
				for (int i = 0; i < 5; i++) {
					Connection connection = connectionPool.getConnection();
					System.out.println(connection.toString());
				}
			} catch (InterruptedException e) {
			}
		});
		t1.start();

		Thread t2 = new Thread(() -> {
			connectionPool.releaseConnection(firstConnection);
		});
		t2.start();
		t1.join();
		t2.join();

	}
}
