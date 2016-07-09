package ranian.bookkeeping.features.transaction.facade.impl;

import java.util.ArrayList;
import java.util.List;

import ranian.bookkeeping.features.transaction.facade.ITransactionFacade;
import ranian.bookkeeping.features.transaction.model.Criteria;
import ranian.bookkeeping.features.transaction.model.Transaction;
import ranian.bookkeeping.system.authentication.model.User;
import ranian.bookkeeping.system.persistence.tables.transaction.dao.ITransactionRecordDAO;
import ranian.bookkeeping.system.persistence.tables.transaction.dao.impl.TransactionRecordDAO;
import ranian.bookkeeping.system.persistence.tables.transaction.vo.TransactionRecordVO;

public class TransactionFacadeImpl implements ITransactionFacade {
	
	@Override
	public boolean createTransaction(User usr, Transaction trans) {
		
		Boolean isSuccess = false;
		
		ITransactionRecordDAO transDao = new TransactionRecordDAO();
		isSuccess = transDao.insertTransactionRecord(usr.getUserId(), trans);
		
		return isSuccess;
	}

	@Override
	public boolean updateTransaction(User usr, Transaction trans) {
		
		Boolean isSuccess = false;
		
		ITransactionRecordDAO transDao = new TransactionRecordDAO();
		isSuccess = transDao.updateTransactionRecord(usr.getUserId(), trans);
		
		return isSuccess;
	}

	@Override
	public List<Transaction> retrieveAllTransactions(User usr) {
		
		ITransactionRecordDAO transactionDao = new TransactionRecordDAO();
		List<TransactionRecordVO> transactionVOs = transactionDao.retrieveAllTransactionRecordsByUser(usr.getUserId());
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		for(TransactionRecordVO transVo : transactionVOs) {
			Transaction transaction = new Transaction(transVo.getRecordId(), 
					transVo.getAmount(), 
					transVo.getTypeId(),
					transVo.getToAcc(), 
					transVo.getFromAcc(),
					transVo.getCategoryId(),
					transVo.getNote(),
					transVo.getRecordTime());
			transactions.add(transaction);
		}
		
		return transactions;
	}

	@Override
	public Transaction getTransactionForEdit(User usr, Integer transId) {
		
		Transaction transaction = null;
		
		Criteria criteria = new Criteria();
		criteria.setRecordIdEqualsTo(transId);
		
		ITransactionRecordDAO transDao = new TransactionRecordDAO();
		TransactionRecordVO transVo = transDao.searchTransactionRecord(usr.getUserId(), criteria).get(0);
		transaction = new Transaction(transVo.getRecordId(), 
				transVo.getAmount(), 
				transVo.getTypeId(), 
				transVo.getToAcc(), 
				transVo.getFromAcc(), 
				transVo.getCategoryId(), 
				transVo.getNote(),
				transVo.getRecordTime());
		
		return transaction;
	}

	@Override
	public Boolean deleteTransaction(User usr, Integer transId) {
		
		Boolean isSuccess = false;
		
		ITransactionRecordDAO transDao = new TransactionRecordDAO();
		isSuccess = transDao.deleteTransactionRecord(usr.getUserId(), transId);
		
		return isSuccess;
	}

}
