package oldProxies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.HashMap;

import com.nevexis.personqueries.constants.Constants;

public class CacheService {
	// to do:check if the table is created in database
	private HashMap<Loadable, Integer> objects;

	public void load(Loadable object) {
		if (!objects.containsKey(object)) {
			loadFromDatabase(object);
		}

	}

	public void loadFromDatabase(Loadable object) {
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "root");
			Statement stmt = conn.createStatement();
			String sql = MessageFormat.format(Constants.getObjectById, object.getClass().getName(), object.getId());
			stmt.execute(sql);
			ResultSet rs = stmt.getResultSet();
			object.load(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(Loadable object) {

	}

	public void save(Loadable object) {

	}

	public Integer getVersion(Loadable object) {
		return objects.get(object);
	}

	public void updateVersion(Loadable object) {
		objects.put(object, objects.get(object) + 1);
	}
}
