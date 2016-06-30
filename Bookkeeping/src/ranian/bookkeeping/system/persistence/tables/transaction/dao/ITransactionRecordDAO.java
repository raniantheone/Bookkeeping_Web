package ranian.bookkeeping.system.persistence.tables.transaction.dao;

import java.util.List;

import ranian.bookkeeping.features.transaction.model.Criteria;
import ranian.bookkeeping.features.transaction.model.Transaction;
import ranian.bookkeeping.system.authentication.model.User;
import ranian.bookkeeping.system.persistence.tables.transaction.vo.TransactionRecordVO;

public interface ITransactionRecordDAO {

	public Boolean insertTransactionRecord(User usr, Transaction trans);
	
	public Boolean updateTransactionRecord();
	
	public List<TransactionRecordVO> retrieveAllTransactionRecordsByUser(Integer userId);
	
	public List<TransactionRecordVO> searchTransactionRecord(Criteria criteria);
	
}
