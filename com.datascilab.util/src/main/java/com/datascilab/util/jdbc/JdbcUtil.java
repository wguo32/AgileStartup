package com.datascilab.util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {

	private static String url = "jdbc:sqlserver://localhost:1433;DataBaseName=db_test";

	private static String username = "sa";

	private static String password = "sa";

	private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	public JdbcUtil() {

		super();
		// TODO Auto-generated constructor stub
	}

	static {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {

		return (Connection) DriverManager
				.getConnection(url, username, password);
	}

	public static void close(ResultSet rs, Statement st, Connection conn) {

		try {
			if (rs != null) {
				rs.close();

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

}
