package ranian.bookkeeping.system.persistence.connection.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import ranian.bookkeeping.system.persistence.connection.IConnectionUtil;

public class DatasourceConnectionUtil implements IConnectionUtil {
	
	private final static IConnectionUtil connUtil;
	
	private DataSource ds;
	
	static {		
		connUtil = new DatasourceConnectionUtil();
	}
	
	public DatasourceConnectionUtil() {

		try {
			
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/bookkeeping_application");
			ds.setLoginTimeout(1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static IConnectionUtil getInstance() {
		return connUtil;
	}
	
	@Override
	public Connection getMysqlConnection() throws OutOfConnectionsException {
		
		Connection conn = null;
		try {
			
			conn = ds.getConnection();
			
		} catch (SQLTimeoutException e) {
			throw new OutOfConnectionsException();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	@Override
	public void returnMysqlConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
