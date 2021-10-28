package com.nevexis;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class Utils {
	public static boolean isExpired(String username) throws SQLException {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/password_demo", "root",
				"frizin12");
				PreparedStatement stmt = conn
						.prepareStatement("SELECT 1 FROM password_demo.user where name = ?  and last_modified + interval 10 day > now();")) {
			stmt.setString(1, username);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next() && rs.getInt(1) == 1) {
				return false;
				}
				return true;
			}
		}
	}
	public static String hash(String password) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		String encoded = Base64.getEncoder().encodeToString(hash);
		return encoded;
	}
	public static boolean isValidInHistory(String username, String password) throws SQLException {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/password_demo", "root",
				"frizin12");
				PreparedStatement stmt = conn
						.prepareStatement("SELECT 1 FROM password_expire pe JOIN `user` u ON  user_id=u.id WHERE u.name = ? AND last_date_modified > now() - interval 60 day AND pe.password = ?;")){
			stmt.setString(1, username);
			stmt.setString(2, password);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next() && rs.getInt(1) == 1) {
					return false;
					}
					return true;
			}
		}
	}
}
