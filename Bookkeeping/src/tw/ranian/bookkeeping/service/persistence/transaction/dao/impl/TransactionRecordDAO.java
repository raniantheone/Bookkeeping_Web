package tw.ranian.bookkeeping.service.persistence.transaction.dao.impl;

import tw.ranian.bookkeeping.authentication.model.User;
import tw.ranian.bookkeeping.service.persistence.transaction.dao.ITransactionRecordDAO;
import tw.ranian.bookkeeping.transaction.model.Transaction;

public class TransactionRecordDAO implements ITransactionRecordDAO {

	@Override
	public Boolean insertATransactionRecord(User usr, Transaction trans) {
		
		String sqlCmd = "";
		
		return null;
	}

	@Override
	public Boolean updateATransactionRecord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer retrieveAllTransactionRecord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer searchTransactionRecord() {
		// TODO Auto-generated method stub
		return null;
	}

}
