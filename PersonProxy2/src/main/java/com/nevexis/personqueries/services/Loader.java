package com.nevexis.personqueries.services;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Locale;

import com.nevexis.personqueries.constants.Constants;
import com.nevexis.personqueries.model.Person;

public class Loader {
	private static final String CONNECTION = "jdbc:mysql://127.0.0.1:3306/sisi";
	private static final String NAME = "root";
	private static final String PASSWORD = "root";
	private static StringBuilder builder = new StringBuilder(20);
	private static StringBuilder builder2 = new StringBuilder(20);

	public static String translater(String field) {
		builder2 = new StringBuilder(20);
		for (char c : field.toCharArray()) {
			if (Character.isUpperCase(c)) {
				builder2.append('_');
				builder2.append(Character.toLowerCase(c));
			} else {
				builder2.append(c);
			}
		}
		return builder2.toString();
	}

	public static String methodTranslater(String field, String method) {
		builder2 = new StringBuilder(method);
		for (String word : field.split("_")) {
			builder2.append(word.substring(0, 1).toUpperCase(Locale.ROOT));
			builder2.append(word.substring(1));
		}
		return builder2.toString();
	}

	public synchronized <T> void generateSchema(Class<T> cl) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(CONNECTION, NAME, PASSWORD);
			Statement stmt = conn.createStatement();
			builder = new StringBuilder("CREATE TABLE " + cl.getSimpleName().toLowerCase() + "(");
			Field[] fields = cl.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				builder.append(translater(fields[i].getName()));
				if (fields[i].getType().equals(Integer.class) || fields[i].getType().equals(Long.class)) {
					builder.append(" BIGINT(19)");
				} else if (fields[i].getType().equals(String.class)) {
					builder.append(" VARCHAR(20)");
				}
				if (fields[i].getName().equals("id"))
					builder.append(" PRIMARY KEY");
				if (i != fields.length - 1)
					builder.append(",");

			}

			builder.append(");");
			stmt.executeUpdate(builder.toString());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static <T> T load(Class<T> cl, Integer id) throws InstantiationException, IllegalAccessException {
		T person = cl.newInstance();
		try (Connection conn = DriverManager.getConnection(CONNECTION, NAME, PASSWORD);
				PreparedStatement stmt = conn
						.prepareStatement(MessageFormat.format(Constants.getObjectById, cl.getSimpleName(), id));
				ResultSet rs = stmt.executeQuery();) {
			while (rs.next()) {
				for (int i = 1; i <= stmt.getMetaData().getColumnCount(); i++) {
					Object object = rs.getObject(i);
					person.getClass()
							.getMethod(methodTranslater(stmt.getMetaData().getColumnName(i), "set"), object.getClass())
							.invoke(person, object);
				}
			}
		} catch (SQLException | IllegalArgumentException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException | SecurityException e1) {
			e1.printStackTrace();
		}
		return person;
	}

	public static <T> void delete(Class<T> class1, Integer id) {

		try {
			Connection conn = DriverManager.getConnection(CONNECTION, NAME, PASSWORD);
			PreparedStatement stmt = conn
					.prepareStatement(MessageFormat.format(Constants.delete, class1.getSimpleName(), id));
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static <T> Person save(Class<T> cl, Person person) {

		try {
			Connection conn = DriverManager.getConnection(CONNECTION, NAME, PASSWORD);
			Statement stmt = conn.createStatement();
			builder = new StringBuilder("UPDATE  " + cl.getSimpleName().toLowerCase() + " SET ");
			Field[] fields = cl.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				String translatedName = translater(fields[i].getName());
				builder.append(translatedName + "=");
				builder.append("'"+cl.getMethod(methodTranslater(translatedName, "get")).invoke(person).toString()+"'");
				if (i != fields.length - 1) {
					builder.append(",");
				}
			}
			builder.append(" where id=");
			builder.append(person.getId());
			stmt.executeUpdate(builder.toString());
			return person;
		} catch (SQLException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return person;

	}
	public static <T> void add(Class<T> cl, Person person) {

		try {
			Connection conn = DriverManager.getConnection(CONNECTION, NAME, PASSWORD);
			Statement stmt = conn.createStatement();
			builder = new StringBuilder("INSERT INTO  " + cl.getSimpleName().toLowerCase() + " VALUES ( ");
			Field[] fields = cl.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				String translatedName = translater(fields[i].getName());
				builder.append("'"+cl.getMethod(methodTranslater(translatedName, "get")).invoke(person).toString()+"'");
				if (i != fields.length - 1) {
					builder.append(",");
				}
			}
			builder.append(")");
			System.out.println(builder);
			stmt.executeUpdate(builder.toString());
		} catch (SQLException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

	}

}
