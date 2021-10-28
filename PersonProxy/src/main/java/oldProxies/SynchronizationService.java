package oldProxies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

import com.nevexis.personqueries.constants.Constants;

import javassist.NotFoundException;
import net.sf.cglib.proxy.Enhancer;

public class SynchronizationService {
	private Enhancer enhancer;
	private CacheService proxy;

	public SynchronizationService() {
		enhancer = new Enhancer();
		enhancer.setSuperclass(CacheService.class);
		proxy = (CacheService) enhancer.create();
	}

	public void delete(Loadable object) {
		proxy.delete(object);
	}

	public void load(Loadable object) {
		proxy.load(object);
	}

	public void save(Loadable object) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "root");
				Statement stmt = conn.createStatement();) {
			String sql = MessageFormat.format(Constants.getObjectById, object.getClass().getName(), object.getId());
			stmt.execute(sql);
			ResultSet rs = stmt.getResultSet();
			Integer cachedVersion = proxy.getVersion(object);
			if (!rs.next())
				throw new NotFoundException("this object is not found");
			if (rs.getInt("id") != cachedVersion) {
				throw new SQLException("optimistic lock failed");
			}
			object.setVersion(cachedVersion++);
			proxy.updateVersion(object);
			proxy.save(object);
		} catch (SQLException | NotFoundException e) {
			e.printStackTrace();
		}
	}

}
