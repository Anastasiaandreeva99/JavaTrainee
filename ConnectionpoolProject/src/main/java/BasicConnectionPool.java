import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnectionPool implements ConnectionPool {
	 private String url;
	    private String user;
	    private String password;
	    private List<Connection> availableConnections = new ArrayList<Connection>();
	    private List<Connection> usedConnections = new ArrayList<Connection>();
	    private static int INITIAL_POOL_SIZE = 10;
	
	    private static ConnectionPool instance;
		static {
			try {
				instance = new BasicConnectionPool("jdbc:mysql://localhost:3306/person?useSSL=false", "root", "root");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	   
		public ConnectionPool getInstance()
		{
			return instance;
		}
	   
	    private  BasicConnectionPool(String url ,String user,String password) throws SQLException
	    {
	    	this.url = url;
	    	this.user = user;
	    	this.password = password;
	    	for(int i =0 ;i<INITIAL_POOL_SIZE;i++)
	    	{
	    		availableConnections.add(BasicConnectionPool.createConnection(url, user, password));
	    	}
	    }
	    public static BasicConnectionPool create(
	      String url, String user, 
	      String password) throws SQLException {
	 
	        List<Connection> pool = new ArrayList<Connection>(INITIAL_POOL_SIZE);
	        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
	            pool.add(createConnection(url, user, password));
	        }
	        return new BasicConnectionPool(url, user, password);
	    }
	    
	    
	    public Connection getConnection() {
	        Connection connection = availableConnections
	          .remove(availableConnections.size() - 1);
	        usedConnections.add(connection);
	        return connection;
	    }
	    
	    public boolean releaseConnection(Connection connection) {
	    	availableConnections.add(connection);
	        return usedConnections.remove(connection);
	    }
	    
	    private static Connection createConnection(
	      String url, String user, String password) 
	      throws SQLException {
	        return DriverManager.getConnection(url, user, password);
	    }
	    
	    public int getSize() {
	        return availableConnections.size() + usedConnections.size();
	    }
		public String getUrl() {
			// TODO Auto-generated method stub
			return url;
		}
		public String getUser() {
			// TODO Auto-generated method stub
			return user;
		}
		public String getPassword() {
			// TODO Auto-generated method stub
			return password;
		}

}
