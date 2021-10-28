package com.nevexis.personqueries.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Objects;

import com.nevexis.personqueries.MyConnectionPool;
import com.nevexis.personqueries.constants.SqlStatements;

public class Utils {

	private static MyConnectionPool connectionPool;
	static {
		try {
			connectionPool = MyConnectionPool.create();
		} catch (SQLException e) {
			throw new Error("ConnectionPool error", e);
		}
	}

	private static MessageDigest digest;
	static {
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new Error("SHA_256 unavailable", e);
		}
	}

	private static boolean findUserHelper(String sqlStatement, String username, String password) throws SQLException {
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sqlStatement)) {
			stmt.setString(1, username);
			if (Objects.nonNull(password)) {
				stmt.setString(2, password);
			}
			try (ResultSet rs = stmt.executeQuery()) {
				return !(rs.next() && rs.getInt(1) == 1);
			}
		}
	}

	public static Connection getConnection() throws SQLException {
		return connectionPool.getConnection();
	}
	public static void releaseConnection(Connection connection) {
		connectionPool.releaseConnection(connection);
		
	}

	public static String hash(String password) throws NoSuchAlgorithmException {
		byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(hash);
	}

	public static boolean isExpired(String username) throws SQLException {
		return findUserHelper(SqlStatements.isPasswordExpire, username, null);
	}

	public static boolean isValidInHistory(String username, String password) throws SQLException {
		return findUserHelper(SqlStatements.isPasswordInHistory, username, password);
	}

}
