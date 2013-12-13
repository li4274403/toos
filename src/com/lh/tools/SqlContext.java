package com.lh.tools;

import com.lh.tools.UtilConstant.DbOperator;

public class SqlContext {
	private String sql;
	private Object[] objs;
	private DbOperator dbop;

	public SqlContext(String sql, DbOperator dbop, Object... objs) {
		this.sql = sql;
		this.objs = objs;
		this.dbop = dbop;
	}

	public String getSql() {
		return sql;
	}

	public Object[] getObjs() {
		return objs;
	}

	public DbOperator getDbop() {
		return dbop;
	}

}
