package oldProxies;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Loadable {
	private Integer version;

	public String translater(String field) {
		StringBuilder result = new StringBuilder(20);
		for (char c : field.toCharArray()) {
			if (Character.isUpperCase(c)) {
				result.append('_');
				result.append(Character.toLowerCase(c));
			}
			result.append(c);
		}
		return result.toString();
	}

	public void load(ResultSet rs) {
		for (Field field : this.getClass().getDeclaredFields()) {
			try {
				if (field.getType().equals(String.class)) {
					field.set(this, rs.getString(translater(field.getName())));
				} else if (field.getType().equals(Long.class)) {
					field.set(this, rs.getLong(translater(field.getName())));
				}
			} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
		}

	}
	public void generateSchema() {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?user=root","root","root");
				Statement stmt = conn.createStatement();) {
			StringBuilder sql = new StringBuilder("CREATE TABLE " + this.getClass().getName() + "(");
			Field[] fields = this.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				sql.append(translater(fields[i].getName()));
				if (fields[i].getType().equals(Integer.class)) {
					sql.append(" INT");
				} else if (fields[i].getType().equals(String.class)) {
					sql.append(" VARCHAR(20)");
				} else if (fields[i].getType().equals(String.class)) {
					sql.append(" LONG");
				}
				if (fields[i].getName().equals("id"))
					sql.append("PRIMARY KEY");
				if (i != fields.length - 1)
					sql.append(",");

			}
			sql.append(");");
			stmt.executeUpdate(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;

	}

	public abstract void save();

	public abstract void delete();

	public abstract Long getId();

}
