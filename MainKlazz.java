package com.nevexis;

import java.sql.SQLException;

public class MainKlazz {

	public static void main(String[] args) throws SQLException {
		//LOGON
		//ProxyFactory.enchanceWithHash(ProxyFactory.enchanceWithExpire(new LoginService())).login("van4o","sisieta");
		
		//changePassword
		
		ProxyFactory.enchanceWithHash(ProxyFactory.enchanceWithValidation(ProxyFactory.enchanceWithHistory(new LoginService()))).changePassword("van4o", "sisietaValidaciq3!", "sisietaValidaciq4!");
	}	
}
