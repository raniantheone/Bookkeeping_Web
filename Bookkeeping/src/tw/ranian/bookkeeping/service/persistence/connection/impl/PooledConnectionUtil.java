package tw.ranian.bookkeeping.service.persistence.connection.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import tw.ranian.bookkeeping.service.persistence.connection.IConnectionUtil;

/**
 * A connection pool to manage MySql connections. The responsibilities include:<br>
 * Provide a connection when the client requires,<br>
 * Store connection when the client returns it,<br>
 * Validate whether the connection in pool is still available,<br>
 * Destroy the connection if its not available,<br>
 * Maintain connections to the maximum quantity specified.
 * 
 * @author ranian
 *
 */
public class PooledConnectionUtil implements IConnectionUtil {  
	
	// FIXME move the sensitive info into properties file
	private static String mysqlConnectorClassName = "com.mysql.jdbc.Driver";

	// FIXME move the sensitive info into properties file
	private static String mysqlConnectionStr = "jdbc:mysql://127.0.0.1:3306/bookkeeping_application";
	
	// FIXME move the sensitive info into properties file
	private static String mysqlAcc = "root";
	
	// FIXME move the sensitive info into properties file
	private static String mysqlPw = "yian";
	
	private final static PooledConnectionUtil connUtil;
	
	private final static Queue<Connection> mysqlConnectionPool;
	
	private final static int maxConns;
	
	private static int spawnedConns;
	
	static {
		
		try {
			Class.forName(mysqlConnectorClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mysqlConnectionPool = new ConcurrentLinkedQueue<Connection>();
		connUtil = new PooledConnectionUtil();
		maxConns = 50;
		spawnedConns = 0;
		
	};
	
	private PooledConnectionUtil() {
		
	};
	
	private Connection createAMysqlConnection() {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(mysqlConnectionStr, mysqlAcc, mysqlPw);
			spawnedConns += 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		
	}
	
	private boolean validateConn(Connection conn) {
		
		boolean isValid = false;
		
		try {
			if( !conn.isClosed() ) {
				isValid = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isValid;
		
	}
	
	private void destroyConn(Connection conn) {
		
		try {
			if( conn != null ) {
				conn.close();
				spawnedConns -= 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static PooledConnectionUtil getInstance() {
		
		return connUtil;
		
	}
	
	@Override
	synchronized public Connection getMysqlConnection() throws OutOfConnectionsException {
		
		Connection conn = null;
		
		if( spawnedConns < maxConns ) {
			conn = mysqlConnectionPool.poll();
		} else {
			throw new OutOfConnectionsException(maxConns);
		};
		
		if( conn == null || !validateConn(conn) ) {
			conn = createAMysqlConnection();	
		}
		
		return conn;
		
	}

	@Override
	synchronized public void returnMysqlConnection(Connection conn) {

		if( !validateConn(conn) ) {
			destroyConn(conn);
		} else {
			mysqlConnectionPool.offer(conn);
		}
		
	}
	
}
