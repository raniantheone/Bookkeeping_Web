package ranian.bookkeeping.system.persistence.connection.impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import ranian.bookkeeping.system.persistence.connection.IConnectionUtil;

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
	
	private static String mysqlConnectorClassName;

	private static String mysqlConnectionStr;
	
	private static String mysqlAcc;
	
	private static String mysqlPw;
	
	private final static PooledConnectionUtil connUtil;
	
	private final static Queue<Connection> mysqlConnectionPool;
	
	private final static int maxConns;
	
	private static int spawnedConns;
	
	static {
		
		try {
			
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream is = classLoader.getResourceAsStream("ranian/bookkeeping/system/resources/MySQL_access.properties");
			Properties properties = new Properties();
			properties.load(is);
			
			mysqlConnectorClassName = properties.getProperty("mysqlConnectorClassName");
			mysqlConnectionStr = properties.getProperty("mysqlConnectionStr");
			mysqlAcc = properties.getProperty("mysqlAcc");
			mysqlPw = properties.getProperty("mysqlPw");
			
			Class.forName(mysqlConnectorClassName);
			
		} catch (Exception e) {
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
