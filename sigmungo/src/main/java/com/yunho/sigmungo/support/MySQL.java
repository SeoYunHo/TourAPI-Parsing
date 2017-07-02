package com.yunho.sigmungo.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {
	private static Connection connection;

	private static String url = "jdbc:mysql://localhost:3306/sigmungo";
	private static String user = "root";
	private static String password = "hy980615";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private synchronized static PreparedStatement buildQuery(String sql, Object... args) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			int placeholderCount = 1;
			for (Object o : args) {
				statement.setObject(placeholderCount++, o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return statement;
	}

	public synchronized static ResultSet executeQuery(String sql, Object... args) {
		try {
			return buildQuery(sql, args).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public synchronized static int executeUpdate(String sql, Object... args) {
		try {
			return buildQuery(sql, args).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
