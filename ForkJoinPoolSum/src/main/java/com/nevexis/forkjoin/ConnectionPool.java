package com.nevexis.forkjoin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
	private static ReentrantLock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	private static List<Connection> usedConnections = new ArrayList<>();
	private static List<Connection> freeConnections = new ArrayList<>();
	private String url;
	private String user;
	private String password;
	private static int SIZE = 2;

	public ConnectionPool(String url, String user, String password, int size) throws SQLException {
		this.url = url;
		this.user = user;
		this.password = password;
		ConnectionPool.SIZE = size;
		init();
	}

	private void init() throws SQLException {
		freeConnections = new ArrayList<>(SIZE);
		usedConnections = new ArrayList<>(SIZE);
		for(int i=0;i<SIZE;i++)
		{
			freeConnections.add(DriverManager.getConnection(url,user,password));
		}
	}

	public Connection getConnection() throws InterruptedException {
		Connection currConnection = null;
		try {
			lock.lock();
			while (freeConnections.isEmpty()) {
				condition.await();			
			} 
				currConnection = freeConnections.remove(freeConnections.size() - 1);
				usedConnections.add(currConnection);
			
		} finally {
			lock.unlock();
		}
		return currConnection;
	}

	public void releaseConnection(Connection connection) {
		try {
			lock.lock();
			if (usedConnections.contains(connection)) {
				usedConnections.remove(connection);
				freeConnections.add(connection);
				System.out.println("Released");
				condition.signal();
			}
		} finally {
			lock.unlock();
		}
		
	}
	public static void main(String[] args) throws SQLException, InterruptedException {
		ConnectionPool connectionPool = new ConnectionPool("jdbc:mysql://localhost:3306/personschema?useSSL=false", "root", "root", 5);
		System.out.println(connectionPool.getConnection().toString());
		connectionPool.releaseConnection(connectionPool.getConnection());
	}
}
