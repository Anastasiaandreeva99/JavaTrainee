package com.nevexis.personqueries;

import java.sql.Connection;

import com.nevexis.personqueries.services.LoginService;
import com.nevexis.personqueries.services.ProxyFactory;

public class App {
	public static void main(String[] args) throws Exception {
		// LOGON
		// ProxyFactory.enchanceWithHash(ProxyFactory.enchanceWithExpire(new
		// LoginService())).login("van4o","sisieta");

		// changePassword
		ProxyFactory
				.enchanceWithHash(
						ProxyFactory.enchanceWithValidation(ProxyFactory.enchanceWithHistory(new LoginService())))
				.changePassword("van4o", "sisietaValidaciq3!", "sisietaValidaciq4!");
	}

}