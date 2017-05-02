package com.xcc.db;

import java.sql.SQLException;

/**
 * 数据库操作异常
 * @author XChao
 */
public class DBException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DBException(String message) {
		super(message);
	}
	
	public DBException(SQLException e) {
		super(e);
	}

	public DBException(String message, SQLException e) {
		super(message, e);
	}
}
