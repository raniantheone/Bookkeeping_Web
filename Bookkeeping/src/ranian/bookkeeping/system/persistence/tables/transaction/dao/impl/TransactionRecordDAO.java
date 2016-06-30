package ranian.bookkeeping.system.persistence.tables.transaction.dao.impl;

import java.util.ArrayList;
import java.util.List;

import ranian.bookkeeping.features.transaction.model.Criteria;
import ranian.bookkeeping.features.transaction.model.Transaction;
import ranian.bookkeeping.system.authentication.model.User;
import ranian.bookkeeping.system.persistence.tables.BaseDAO;
import ranian.bookkeeping.system.persistence.tables.transaction.dao.ITransactionRecordDAO;
import ranian.bookkeeping.system.persistence.tables.transaction.vo.TransactionRecordVO;

public class TransactionRecordDAO extends BaseDAO implements ITransactionRecordDAO {
	
	public TransactionRecordDAO() {
		super();
	}
	
	@Override
	public Boolean insertTransactionRecord(User usr, Transaction trans) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateTransactionRecord() {
		// TODO Auto-generated method stub
		return null;
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
	public List<TransactionRecordVO> searchTransactionRecord(Criteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
