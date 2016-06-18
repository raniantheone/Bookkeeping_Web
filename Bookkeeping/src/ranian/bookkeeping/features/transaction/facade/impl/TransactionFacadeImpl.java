package ranian.bookkeeping.features.transaction.facade.impl;

import java.util.List;

import ranian.bookkeeping.features.transaction.facade.ITransactionFacade;
import ranian.bookkeeping.features.transaction.model.Criteria;
import ranian.bookkeeping.features.transaction.model.Transaction;
import ranian.bookkeeping.system.authentication.model.User;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> searchTransactions(User usr, Criteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
