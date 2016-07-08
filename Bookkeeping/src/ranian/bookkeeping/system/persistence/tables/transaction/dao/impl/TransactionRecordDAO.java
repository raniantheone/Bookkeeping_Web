package ranian.bookkeeping.system.persistence.tables.transaction.dao.impl;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import ranian.bookkeeping.features.transaction.model.Criteria;
import ranian.bookkeeping.features.transaction.model.Transaction;
import ranian.bookkeeping.system.persistence.tables.BaseDAO;
import ranian.bookkeeping.system.persistence.tables.transaction.dao.ITransactionRecordDAO;
import ranian.bookkeeping.system.persistence.tables.transaction.vo.TransactionRecordVO;

public class TransactionRecordDAO extends BaseDAO implements ITransactionRecordDAO {
	
	public TransactionRecordDAO() {
		super();
	}
	
	@Override
	public Boolean insertTransactionRecord(Integer usrId, Transaction trans) {
		
		String sqlCmd = " insert into TR_RECORD "
				+ "(AMOUNT, TYPE_ID, "
				+ "TO_ACC_ID, FROM_ACC_ID, "
				+ "CATEGORY_ID, NOTE, "
				+ "RECORD_TIME, USER_ID) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		Boolean isSuccess = false;
		try{
			
			conn = connUtil.getMysqlConnection();
			pstmt = conn.prepareStatement(sqlCmd);
			pstmt.setFloat(1, trans.getTransAmount());
			pstmt.setInt(2, trans.getTransType());
			
			if( trans.getToAccId() == null ) {
				
				pstmt.setNull(3, Types.INTEGER);
				
			} else {
				
				pstmt.setInt(3, trans.getToAccId());
				
			}
			
			if( trans.getFromAccId() == null ) {
				
				pstmt.setNull(4, Types.INTEGER);
				
			} else {
				
				pstmt.setInt(4, trans.getFromAccId());
				
			}
			
			pstmt.setInt(5, trans.getTransCategory());
			pstmt.setString(6, trans.getTransNote());
			pstmt.setTimestamp(7, trans.getTransRecordTime());
			pstmt.setInt(8, usrId);
			int rowCount = pstmt.executeUpdate();
			isSuccess = rowCount > 0 ? true : false;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(pstmt, conn);
		}
		
		return isSuccess;
	}

	@Override
	public Boolean updateTransactionRecord(Integer usrId, Transaction trans) {

		String sqlCmd = "update TR_RECORD "
				+ "set AMOUNT=?, TYPE_ID=?, "
				+ "TO_ACC_ID=?, FROM_ACC_ID=?, "
				+ "CATEGORY_ID=?, NOTE=?, "
				+ "RECORD_TIME=? "
				+ "where RECORD_ID=? "
				+ "and USER_ID=?";
		
		Boolean isSuccess = false;
		try{
			
			conn = connUtil.getMysqlConnection();
			pstmt = conn.prepareStatement(sqlCmd);
			pstmt.setFloat(1, trans.getTransAmount());
			pstmt.setInt(2, trans.getTransType());
			
			if( trans.getToAccId() == null ) {
				
				pstmt.setNull(3, Types.INTEGER);
				
			} else {
				
				pstmt.setInt(3, trans.getToAccId());
				
			}
			
			if( trans.getFromAccId() == null ) {
				
				pstmt.setNull(4, Types.INTEGER);
				
			} else {
				
				pstmt.setInt(4, trans.getFromAccId());
				
			}
			
			pstmt.setInt(5, trans.getTransCategory());
			pstmt.setString(6, trans.getTransNote());
			pstmt.setTimestamp(7, trans.getTransRecordTime());
			pstmt.setInt(8, trans.getTransRecordId());
			pstmt.setInt(9, usrId);
			int rowCount = pstmt.executeUpdate();
			isSuccess = rowCount > 0 ? true : false;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(pstmt, conn);
		}
		
		return isSuccess;
	}

	@Override
	public List<TransactionRecordVO> retrieveAllTransactionRecordsByUser(Integer userId) {
		
		String sqlCmd = "select RECORD_ID, AMOUNT, TYPE_ID, "
				+ "TO_ACC_ID, FROM_ACC_ID, CATEGORY_ID, "
				+ "NOTE, RECORD_TIME, RECORD_DEL, "
				+ "USER_ID "
				+ "from TR_RECORD where USER_ID = ?";
		
		List<TransactionRecordVO> transactionVOs = new ArrayList<TransactionRecordVO>();
		try{
			
			conn = connUtil.getMysqlConnection();
			pstmt = conn.prepareStatement(sqlCmd);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				TransactionRecordVO transVo = new TransactionRecordVO();
				transVo.setRecordId(rs.getInt("RECORD_ID"));
				transVo.setAmount(rs.getFloat("AMOUNT"));
				transVo.setTypeId(rs.getInt("TYPE_ID"));
				transVo.setToAcc(rs.getInt("TO_ACC_ID"));
				transVo.setFromAcc(rs.getInt("FROM_ACC_ID"));
				transVo.setCategoryId(rs.getInt("CATEGORY_ID"));
				transVo.setNote(rs.getString("NOTE"));
				transVo.setRecordTime(rs.getTimestamp("RECORD_TIME"));
				transVo.setRecordDel(rs.getBoolean("RECORD_DEL"));
				transVo.setUserId(rs.getInt("USER_ID"));
				transactionVOs.add(transVo);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(rs, pstmt, conn);
		}
		
		return transactionVOs;
	}

	@Override
	public List<TransactionRecordVO> searchTransactionRecord(Integer userId, Criteria criteria) {
		
		String baseCmd = "select RECORD_ID, AMOUNT, TYPE_ID, "
				+ "TO_ACC_ID, FROM_ACC_ID, CATEGORY_ID, "
				+ "NOTE, RECORD_TIME, RECORD_DEL, "
				+ "USER_ID "
				+ "from TR_RECORD where USER_ID = ?";
		
		StringBuilder sb = new StringBuilder();
		sb.append(baseCmd);
		
		Set<Entry<String, Object>> criteriaEntrySet = criteria.getCriteriaMap().entrySet();
		for( Entry<String, Object> entry : criteriaEntrySet ) {
			if( entry != null ) {
				sb.append(" and ").append(entry.getKey()).append("?");
			}
		}
		String sqlCmd = sb.toString();
		
		List<TransactionRecordVO> transactionVOs = new ArrayList<TransactionRecordVO>();
		try{
		
			conn = connUtil.getMysqlConnection();
			pstmt = conn.prepareStatement(sqlCmd);
			pstmt.setInt(1, userId);
			
			int paramIndex = 2;
			for( Entry<String, Object> entry : criteriaEntrySet ) {
				
				Object criteriaValue = entry.getValue();
				if( criteriaValue instanceof Integer ) {
					pstmt.setInt(paramIndex, (Integer) criteriaValue);
				}else if( criteriaValue instanceof Float ) {
					pstmt.setFloat(paramIndex, (Float) criteriaValue);
				}else if( criteriaValue instanceof String ) {
					pstmt.setString(paramIndex, (String) criteriaValue);
				}else if( criteriaValue instanceof Timestamp ) {
					pstmt.setTimestamp(paramIndex, (Timestamp) criteriaValue);
				}
				
				paramIndex++;
			}
			
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				TransactionRecordVO transVo = new TransactionRecordVO();
				transVo.setRecordId(rs.getInt("RECORD_ID"));
				transVo.setAmount(rs.getFloat("AMOUNT"));
				transVo.setTypeId(rs.getInt("TYPE_ID"));
				transVo.setToAcc(rs.getInt("TO_ACC_ID"));
				transVo.setFromAcc(rs.getInt("FROM_ACC_ID"));
				transVo.setCategoryId(rs.getInt("CATEGORY_ID"));
				transVo.setNote(rs.getString("NOTE"));
				transVo.setRecordTime(rs.getTimestamp("RECORD_TIME"));
				transVo.setRecordDel(rs.getBoolean("RECORD_DEL"));
				transVo.setUserId(rs.getInt("USER_ID"));
				transactionVOs.add(transVo);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(rs, pstmt, conn);
		}
		
		return transactionVOs;
	}

}
