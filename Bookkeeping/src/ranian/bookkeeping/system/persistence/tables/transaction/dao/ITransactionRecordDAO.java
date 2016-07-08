package ranian.bookkeeping.system.persistence.tables.transaction.dao;

import java.util.List;

import ranian.bookkeeping.features.transaction.model.Criteria;
import ranian.bookkeeping.features.transaction.model.Transaction;
import ranian.bookkeeping.system.persistence.tables.transaction.vo.TransactionRecordVO;

public interface ITransactionRecordDAO {

	public Boolean insertTransactionRecord(Integer usrId, Transaction trans);
	
	public Boolean updateTransactionRecord(Integer usrId, Transaction trans);
	
	public List<TransactionRecordVO> retrieveAllTransactionRecordsByUser(Integer userId);
	
	public List<TransactionRecordVO> searchTransactionRecord(Integer userId, Criteria criteria);
	
}
