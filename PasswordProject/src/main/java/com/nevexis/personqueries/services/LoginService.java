package com.nevexis.personqueries.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nevexis.personqueries.constants.SqlStatements;

public class LoginService {
	public void login(String username, String password) throws SQLException {
		try (Connection conn = Utils.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlStatements.getUser)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next() ) {
					System.out.println("Login successfull");
				} else {
					throw new IllegalArgumentException("Invalid password or username");
				}
			}
		}
	}

	public void changePassword(String username, String oldPassword, String newPassword) throws SQLException {
		try (Connection conn = Utils.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlStatements.getUser)) {
			try {
				conn.setAutoCommit(false);
				changePasswordHelper(username, oldPassword, newPassword, conn, stmt);
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				throw new RuntimeException("Error in transaction", e);
			}

		}

	}

	private void changePasswordHelper(String username, String oldPassword, String newPassword, Connection conn,
			PreparedStatement stmt) throws SQLException {
		stmt.setString(1, username);
		stmt.setString(2, oldPassword);
		try (ResultSet rs = stmt.executeQuery()) {
			if (rs.next() && rs.getInt(1) == 1) {
				rs.updateString("password", newPassword);
				rs.updateRow();
				try (PreparedStatement stmt3 = conn.prepareStatement(SqlStatements.insertInHistory)) {
					stmt3.setString(1, username);
					stmt3.setString(2, newPassword);
					stmt3.executeUpdate();
				}
				System.out.println("Password changed successfully");

			} else {
				System.out.println("Invalid username or password");
			}
		}
	}
}
