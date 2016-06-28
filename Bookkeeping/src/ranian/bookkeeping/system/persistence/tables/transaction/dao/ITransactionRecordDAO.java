package ranian.bookkeeping.system.persistence.tables.transaction.dao;

import java.util.List;

import ranian.bookkeeping.features.transaction.model.Criteria;
import ranian.bookkeeping.features.transaction.model.Transaction;
import ranian.bookkeeping.system.authentication.model.User;

public interface ITransactionRecordDAO {

	public Boolean insertTransactionRecord(User usr, Transaction trans);
	
	public Boolean updateTransactionRecord();
	
	public List<Transaction> retrieveAllTransactionRecordsByUser(Integer userId);
	
	public List<Transaction> searchTransactionRecord(Criteria criteria);
	
}
