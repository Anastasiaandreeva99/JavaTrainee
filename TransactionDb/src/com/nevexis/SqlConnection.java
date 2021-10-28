package com.nevexis;
import java.sql.*;  
public  class SqlConnection {
	
	 public void  connect() throws ClassNotFoundException, SQLException
	 {
		 Class.forName("com.mysql.jdbc.Driver");  
		 Connection con=DriverManager.getConnection(  
		 "jdbc:mysql://localhost:3306/test1","root","root");  
		 Statement st=con.createStatement();  
		  con.setAutoCommit(false);
			
		st.execute("DELETE FROM phone where personID =1;");  
		st.execute("DELETE FROM phone where personID =2;");  
		st.execute("INSERT INTO phone (phone,personID) values ('083724874',1)");
		st.execute("INSERT INTO phone (phone,personID) values ('034439744',1)");
		st.execute("INSERT INTO phone (phone,personID) values ('07864320',1)");
		st.execute("INSERT INTO phone (phone,personID) values ('08856434',1)");
		ResultSet res= st.executeQuery("SELECT * FROM phone where personID = 1");
		while(res.next())
		{
			System.out.println(res.toString());
		}
	    con.commit();
		 
	 }

}
