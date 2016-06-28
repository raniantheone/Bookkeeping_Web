package ranian.bookkeeping.system.persistence.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ranian.bookkeeping.system.persistence.connection.impl.PooledConnectionUtil;

public class BaseDAO {
	
	
	public PooledConnectionUtil connUtil;
	
	public Connection conn = null;
	
	public PreparedStatement pstmt;
	
	public ResultSet rs;
	
	public BaseDAO() {
		connUtil = PooledConnectionUtil.getInstance();
	}
	
	public void closeResources(PreparedStatement pstmt, Connection conn) {
		
		try{
			if( pstmt != null ) {
				pstmt.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		try{
			if( conn != null ) {
				connUtil.returnMysqlConnection(conn);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeResources(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		
		try{
			if( rs != null ) {
				rs.close();
			}				
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.closeResources(pstmt, conn);

	}
	
}
