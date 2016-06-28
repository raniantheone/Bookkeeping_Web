package ranian.bookkeeping.system.persistence.tables.transaction.dao.impl;

import java.util.List;

import ranian.bookkeeping.features.transaction.model.Criteria;
import ranian.bookkeeping.features.transaction.model.Transaction;
import ranian.bookkeeping.system.authentication.model.User;
import ranian.bookkeeping.system.persistence.tables.transaction.dao.ITransactionRecordDAO;

public class TransactionRecordDAO implements ITransactionRecordDAO {

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
	public List<Transaction> retrieveAllTransactionRecordsByUser(Integer userId) {
		
		ITransactionRecordDAO transactionDao = new TransactionRecordDAO();
		List<Transaction> transactionRecords = transactionDao.retrieveAllTransactionRecordsByUser(userId);
		
		return transactionRecords;
	}

	@Override
	public List<Transaction> searchTransactionRecord(Criteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
