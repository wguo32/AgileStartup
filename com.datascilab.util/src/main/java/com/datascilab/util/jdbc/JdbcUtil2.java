package com.datascilab.util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil2 {

	static {
		try {
			// 加载驱动
			Class.forName(ConfigUtil.getDriver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {

		Connection conn = null;
		String url = ConfigUtil.getUrl();// 获取URL
		String user = ConfigUtil.getUser();// 获取登录名
		String pwd = ConfigUtil.getPwd();// 获取登录密码
		try {
			// 连接数据库
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 释放资源
	public static void release(Object o) {

		if (o == null) {
			return;
		}
		if (o instanceof ResultSet) {
			try {
				((ResultSet) o).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (o instanceof Statement) {
			try {
				((Statement) o).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (o instanceof Connection) {
			Connection c = (Connection) o;
			try {
				if (!c.isClosed()) {
					c.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void release(ResultSet rs, Statement stmt, Connection conn) {

		release(rs);
		release(stmt);
		release(conn);
	}
}
