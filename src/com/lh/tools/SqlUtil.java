package com.lh.tools;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

public abstract class SqlUtil {

	public static Connection getCon(String url, String user, String pwd,
			String driverName, String driverClass) throws SQLException,
			ClassNotFoundException {
		Class.forName(driverClass);
		Connection con = DriverManager.getConnection(url, user, pwd);
		return con;
	}

	public static void exec(Connection conn, List<SqlContext> list) throws SQLException {
		if (list == null || list.size() == 0)
			return;
		boolean autoCommitFlag = conn.getAutoCommit();
		if (autoCommitFlag)
			conn.setAutoCommit(false);
		Savepoint point = conn.setSavepoint();
		try {
			SqlContext dbsql = null;
			PreparedStatement ps = null;
			for (int i = 0; i < list.size(); i++) {
				dbsql = list.get(i);
				ps = conn.prepareStatement(dbsql.getSql());
				setExecAgs(ps, dbsql.getObjs());
				switch (dbsql.getDbop()) {
				case insert:
					ps.executeUpdate();
					break;
				case delete:
					ps.executeUpdate();
					break;
				case update:
					ps.executeUpdate();
					break;
				case select:
					ps.executeQuery();
					break;
				case callInsert:
					ps.executeUpdate();
					break;
				case calldelete:
					ps.executeUpdate();
					break;
				case callUpdate:
					ps.executeUpdate();
					break;
				case callselect:
					ps.executeQuery();
					break;
				default:
					throw new SQLException("no execute task!");
				}
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback(point);
		}
		if (autoCommitFlag)
			conn.setAutoCommit(autoCommitFlag);
	}

	public static ResultSet execQueryBySql(Connection conn, String sql,
			Object... objs) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql);
		setExecAgs(ps, objs);
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	public static ResultSet execQueryByPro(Connection conn, String sql,
			Object... objs) throws SQLException {
		CallableStatement cs = conn.prepareCall(sql);
		setExecAgs(cs, objs);
		ResultSet rs = cs.executeQuery();
		return rs;
	}

	public static void setExecAgs(PreparedStatement ps, Object... objs)
			throws SQLException {
		for (int i = 0; objs != null && i < objs.length; i++) {
			ps.setObject(i, objs[i]);
		}
	}

	public static String jointArgs(Object... objs) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; objs != null && i < objs.length;) {
			if (sb.length() == 0)
				sb.append("?");
			else
				sb.append(",?");
		}
		return sb.toString();
	}
}
