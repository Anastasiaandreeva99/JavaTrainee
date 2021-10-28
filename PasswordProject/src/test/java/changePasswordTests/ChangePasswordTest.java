package changePasswordTests;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.nevexis.personqueries.constants.SqlStatements;
import com.nevexis.personqueries.services.LoginService;
import com.nevexis.personqueries.services.ProxyFactory;
import com.nevexis.personqueries.services.Utils;

public class ChangePasswordTest {
	private void truncate() throws SQLException {
		try (Connection conn =Utils.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlStatements.truncateHistory);
				PreparedStatement stmt2 = conn.prepareStatement(SqlStatements.deleteUsers)) {
			{
				stmt.executeUpdate();
				stmt2.executeUpdate();
			}
		}
	}

	private void insertPerson(String username, String password) throws SQLException {
		try (Connection conn =Utils.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlStatements.insertNewPerson)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.executeUpdate();
		}
	}

	private void insertInHistory(String username, String password) throws SQLException {
		try (Connection conn =Utils.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlStatements.insertInHistory)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.executeUpdate();
		}
	}

	private void insertInHistoryExpired(String username, String password) throws SQLException {
		try (Connection conn =Utils.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlStatements.insertInHistoryExpired)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.executeUpdate();
		}
	}

	@Test
	public void testChangePassword() throws SQLException, NoSuchAlgorithmException {
		LoginService loginService = ProxyFactory.enchanceWithHash(
				ProxyFactory.enchanceWithValidation(ProxyFactory.enchanceWithHistory(new LoginService())));
		truncate();
		insertPerson("vancho", Utils.hash("Vancho2?"));
		insertInHistory("vancho", Utils.hash("Vancho1?"));
		insertInHistory("vancho", Utils.hash("Vancho0?"));
		insertInHistoryExpired("vancho", Utils.hash("Vancho3?"));
		loginService.changePassword("vancho", "Vancho2?", "Vancho3?");

	}

	@Test(expected = InvocationTargetException.class)
	public void testWithRedundantPassword() throws SQLException, NoSuchAlgorithmException {
		LoginService loginService = ProxyFactory.enchanceWithHash(
				ProxyFactory.enchanceWithValidation(ProxyFactory.enchanceWithHistory(new LoginService())));
		truncate();
		insertPerson("vancho", Utils.hash("Vancho2?"));
		insertInHistory("vancho", Utils.hash("Vancho1?"));
		insertInHistory("vancho", Utils.hash("Vancho0?"));
		insertInHistory("vancho", Utils.hash("Vancho3?"));
		loginService.changePassword("vancho", "Vancho2?", "Vancho3?");

	}
	@Test(expected = IllegalArgumentException.class)
	public void testNotValidPassword() throws SQLException, NoSuchAlgorithmException {
		LoginService loginService = ProxyFactory.enchanceWithValidation(
				ProxyFactory.enchanceWithHash(ProxyFactory.enchanceWithHistory(new LoginService())));
		truncate();
		insertPerson("vancho", Utils.hash("Vancho2?"));
		insertInHistory("vancho", Utils.hash("Vancho1?"));
		insertInHistory("vancho", Utils.hash("Vancho0?"));
		insertInHistory("vancho", Utils.hash("Vancho3?"));
		loginService.changePassword("vancho", "Vancho2?", "Vancho");

	}
}
