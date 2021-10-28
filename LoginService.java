package com.nevexis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
	public void login(String username, String password) throws SQLException {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/password_demo", "root",
				"frizin12");
				PreparedStatement stmt = conn
						.prepareStatement("SELECT 1 FROM password_demo.user where name = ? and password = ?")) {
			stmt.setString(1, username);
			stmt.setString(2, password);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next() && rs.getInt(1) == 1) {
					System.out.println("Login successfull");
				} else
					System.out.println("Invalid username or password");
			}
		}
	}

	public void changePassword(String username, String oldPassword, String newPassword) throws SQLException {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/password_demo", "root",
				"frizin12");
				PreparedStatement stmt = conn
						.prepareStatement("SELECT 1 FROM password_demo.user where name = ? and password = ?")) {
			stmt.setString(1, username);
			stmt.setString(2, oldPassword);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next() && rs.getInt(1) == 1) {
					try (PreparedStatement stmt2 = conn.prepareStatement(
							"UPDATE `password_demo`.`user` SET `password` = ? , last_modified = now() WHERE (`id` = '1');")) {
						stmt2.setString(1, newPassword);
						stmt2.executeUpdate();
					}
					try (PreparedStatement stmt3 = conn.prepareStatement(
							"INSERT INTO `password_demo`.`password_expire` (`last_date_modified`, `user_id`, `password`) VALUES (now(), (SELECT id FROM `user` WHERE `name` = ? LIMIT 1), ?);")) {
						stmt3.setString(1, username);
						stmt3.setString(2, newPassword);
						stmt3.executeUpdate();
					}
					System.out.println("Password changed successfully");

				} else
					System.out.println("Invalid username or password");
			}
		}
	}
}
