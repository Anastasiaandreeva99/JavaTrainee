package com.nevexis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainClass {

	public static void main(String[] args)throws ClassNotFoundException, SQLException {

		connect();

	}
	 public static void  connect() throws ClassNotFoundException, SQLException
	 {
		 Class.forName("com.mysql.cj.jdbc.Driver");  
		 Connection con=DriverManager.getConnection(  
		 "jdbc:mysql://localhost:3306/test1","root","root");  
		 Statement st=con.createStatement();  
		  con.setAutoCommit(false);
			try{
				
			
		st.execute("DELETE FROM phone where personID =1;");  
		st.execute("DELETE FROM phone where personID =2;");  
		st.execute("INSERT INTO phone (number,personID) values ('083724874',1)");
		st.execute("INSERT INTO phone (number,personID) values ('034439744',1)");
		st.execute("INSERT INTO phone (number,personID) values ('07864320',1)");
		st.execute("INSERT INTO phone (number,personID) values ('08856434',1)");
		ResultSet res= st.executeQuery("SELECT * FROM phone where personID = 1");
		while(res.next())
		{
			System.out.println(res.getString("number"));
		}
		
			
	    con.commit();
			}
			catch(Exception e)
			{
				con.rollback();
			}
		 
	 }
}
