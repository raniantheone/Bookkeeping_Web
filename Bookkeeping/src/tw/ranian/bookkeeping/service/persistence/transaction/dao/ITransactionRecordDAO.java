package tw.ranian.bookkeeping.service.persistence.transaction.dao;

import tw.ranian.bookkeeping.authentication.model.User;
import tw.ranian.bookkeeping.transaction.model.Transaction;

public interface ITransactionRecordDAO {

	public Boolean insertATransactionRecord(User usr, Transaction trans);
	
	public Boolean updateATransactionRecord();
	
	public Integer retrieveAllTransactionRecord();
	
	public Integer searchTransactionRecord();
	
}
