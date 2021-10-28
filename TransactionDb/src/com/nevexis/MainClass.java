package com.nevexis;

import java.sql.SQLException;

public class MainClass {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SqlConnection connection = new SqlConnection();
		connection.connect();

	}

}
