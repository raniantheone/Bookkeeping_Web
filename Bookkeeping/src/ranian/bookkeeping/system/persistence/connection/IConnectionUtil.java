package ranian.bookkeeping.system.persistence.connection;

import java.sql.Connection;

public interface IConnectionUtil {
	
	@SuppressWarnings("serial")
	public static class OutOfConnectionsException extends Exception {
		
		public OutOfConnectionsException () {};
		
		public OutOfConnectionsException (int maxConns) {
			super( String.format("Max connections  of the pool is reached: %d%n", maxConns) );
		};
		
	}
	
	public Connection getMysqlConnection() throws OutOfConnectionsException;
	
	public void returnMysqlConnection(Connection conn);
	
}
