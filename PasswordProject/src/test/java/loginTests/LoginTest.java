package loginTests;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.nevexis.personqueries.constants.SqlStatements;
import com.nevexis.personqueries.services.LoginService;
import com.nevexis.personqueries.services.ProxyFactory;
import com.nevexis.personqueries.services.Utils;

public class LoginTest {
	@Before
	public void truncate() throws SQLException {
		try (Connection conn = Utils.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlStatements.truncateHistory);
				PreparedStatement stmt2 = conn.prepareStatement(SqlStatements.deleteUsers)) {
			{
				stmt.executeUpdate();
				stmt2.executeUpdate();
			}
		}
	}

	private void insertPerson(String username, String password) throws SQLException {
		try (Connection conn = Utils.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlStatements.insertNewPerson)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.executeUpdate();
		}
	}

	private void insertExpiredPerson(String username, String password) throws SQLException {
		try (Connection conn = Utils.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlStatements.insertExpiredPerson)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.executeUpdate();
		}
	}

	@Test
	public void testPureLogin() throws SQLException {
		LoginService loginService = new LoginService();
 		insertPerson("vancho", "Vancho2?");
		loginService.login("vancho", "Vancho2?");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testPureLoginFalsePassword() throws SQLException {
		LoginService loginService = new LoginService();
 		insertPerson("vancho", "Vancho2?");
		loginService.login("vancho", "Vancho3?");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testPureLoginFalseUsername() throws SQLException {
		LoginService loginService = new LoginService();
 		insertPerson("vancho", "Vancho2?");
		loginService.login("vancho1", "Vancho2?");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPureLoginFalseBoth() throws SQLException {
		LoginService loginService = new LoginService();
 		insertPerson("vancho", "Vancho2?");
		loginService.login("vancho1", "Vancho3?");
	}

	@Test()
	public void testLogin() throws SQLException, NoSuchAlgorithmException {
		LoginService loginService = ProxyFactory.enchanceWithHash(ProxyFactory.enchanceWithExpire(new LoginService()));
 		insertPerson("vancho", Utils.hash("Vancho2?"));
		loginService.login("vancho", "Vancho2?");

	}

	@Test(expected = InvocationTargetException.class)
	public void testLoginFalsePassword() throws SQLException, NoSuchAlgorithmException {
		LoginService loginService = ProxyFactory.enchanceWithHash(ProxyFactory.enchanceWithExpire(new LoginService()));
 		insertPerson("vancho", Utils.hash("Vancho2?"));
		loginService.login("vancho", "Vancho3?");

	}

	@Test(expected = InvocationTargetException.class)
	public void testLoginExpired() throws SQLException, NoSuchAlgorithmException {
		LoginService loginService = ProxyFactory.enchanceWithHash(ProxyFactory.enchanceWithExpire(new LoginService()));
 		insertExpiredPerson("vancho", Utils.hash("Vancho2?"));
		loginService.login("vancho", "Vancho2?");

	}

}
