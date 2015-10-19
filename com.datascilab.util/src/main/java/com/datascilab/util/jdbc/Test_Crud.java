package com.datascilab.util.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Test_Crud {

	private static void add() throws SQLException {

		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "insert into [user](name,password,email,age,birthday,money) values(?,?,?,?,?,?)";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, "psName");
			ps.setString(2, "psPassword");
			ps.setString(3, "jkjs@126.com");
			ps.setInt(4, 23);
			ps.setDate(5, new java.sql.Date(new java.util.Date().getDate()));
			ps.setFloat(6, 2344);
			ps.executeUpdate();
		} finally {
			JdbcUtil.close(rs, ps, conn);
		}

	}

	private static void get() throws SQLException {

		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "select id,name,password,email,birthday,money from [user]";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String pass = rs.getString("password");
				String email = rs.getString("email");
				Date birthday = rs.getDate("birthday");
				float money = rs.getFloat("money");
				System.out.println("id是：" + id + "姓名是： " + name + " 密码是："
						+ pass + "邮箱是：" + email + "生日是： " + birthday + "工资是"
						+ money);
			}

		} finally {
			JdbcUtil.close(rs, ps, conn);
		}

	}

	private static void update() throws SQLException {

		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "update [user] set name='lucy',password='123',money=5000 where id=1";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.executeUpdate();

		} finally {
			JdbcUtil.close(rs, ps, conn);
		}

	}

	private static void delete() throws SQLException {

		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "delete from [user] where id=1";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.executeUpdate();

		} finally {
			JdbcUtil.close(rs, ps, conn);
		}

	}

	public static void transferTest() throws SQLException {

		int id1 = 3;
		float m1 = 100.0f;
		int id2 = 4;
		transfer2(id1, m1, id2);
	}

	public static boolean transfer2(int id1, float m1, int id2)
			throws SQLException {

		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			String sql = "update [user] set money = money=? where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setFloat(1, m1);
			ps.setInt(2, id1);
			ps.executeUpdate();

			String sql1 = "update [user] set money = money+? where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setFloat(1, m1);
			ps.setInt(2, id2);
			ps.executeUpdate();

			conn.commit();
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			conn.rollback();
			throw e;
		} finally {
			JdbcUtil.close(rs, ps, conn);
		}
		return flag;

	}

	public static void main(String[] args) {

	}

}
