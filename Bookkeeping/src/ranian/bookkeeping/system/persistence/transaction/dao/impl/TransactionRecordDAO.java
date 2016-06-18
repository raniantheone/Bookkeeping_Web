package ranian.bookkeeping.system.persistence.transaction.dao.impl;

import ranian.bookkeeping.features.transaction.model.Transaction;
import ranian.bookkeeping.system.authentication.model.User;
import ranian.bookkeeping.system.persistence.transaction.dao.ITransactionRecordDAO;

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
