package com.nevexis.personqueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyConnectionPool {

	private static String CONNECTION_STRING = "jdbc:mysql://127.0.0.1:3306/sisi";
	private static String CONNECTION_USERNAME = "root";
	private static String CONNECTION_PASSWORD = "root";
	private static int INITIAL_POOL_SIZE = 10;
	private static int MAX_POOL_SIZE = 60;

	private List<Connection> connectionPool;
	private List<Connection> usedConnections = new ArrayList<>();

	public MyConnectionPool(List<Connection> connectionPool) {
		this.connectionPool = connectionPool;
		this.usedConnections = new ArrayList<Connection>();
	}

	public static MyConnectionPool create() throws SQLException {

		List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
		for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
			pool.add(createConnection());
		}
		return new MyConnectionPool(pool);
	}

	public Connection getConnection() throws SQLException {
		if (connectionPool.isEmpty()) {
			if (usedConnections.size() < MAX_POOL_SIZE) {
				connectionPool.add(createConnection());
			} else {
				throw new RuntimeException("No available connections!");
			}
		}
		Connection connection = connectionPool.remove(connectionPool.size() - 1);
		usedConnections.add(connection);
		return connection;
	}

	public boolean releaseConnection(Connection connection) {
		connectionPool.add(connection);
		return usedConnections.remove(connection);
	}

	private static Connection createConnection() throws SQLException {
		return DriverManager.getConnection(CONNECTION_STRING, CONNECTION_USERNAME, CONNECTION_PASSWORD);
	}

	public void shutdown() throws SQLException {
	    usedConnections.forEach(this::releaseConnection);
	    for (Connection c : connectionPool) {
	        c.close();
	    }
	    connectionPool.clear();
	}

}
