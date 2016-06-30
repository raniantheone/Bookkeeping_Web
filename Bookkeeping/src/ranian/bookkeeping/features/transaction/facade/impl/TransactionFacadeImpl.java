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
	
	// private dao interface
	
	@Override
	public boolean createTransaction(User usr, Transaction trans) {
		
		/*
		
		call dao to perform db insert
		
		 */
		
		return false;
	}

	@Override
	public boolean updateTransaction(User usr, Transaction trans) {
		// TODO Auto-generated method stub
		return false;
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
	public List<Transaction> searchTransactions(User usr, Criteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
