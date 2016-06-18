package ranian.bookkeeping.system.persistence.transaction.dao;

import ranian.bookkeeping.features.transaction.model.Transaction;
import ranian.bookkeeping.system.authentication.model.User;

public interface ITransactionRecordDAO {

	public Boolean insertATransactionRecord(User usr, Transaction trans);
	
	public Boolean updateATransactionRecord();
	
	public Integer retrieveAllTransactionRecord();
	
	public Integer searchTransactionRecord();
	
}
