package ranian.bookkeeping.features.transaction.facade;

import java.util.List;

import ranian.bookkeeping.features.transaction.model.Criteria;
import ranian.bookkeeping.features.transaction.model.Transaction;
import ranian.bookkeeping.system.authentication.model.User;

public interface ITransactionFacade {

	/**
	 * Create a transaction record
	 * @param usr
	 * @param trans
	 * @return
	 */
	public boolean createTransaction(User usr, Transaction trans);
	
	/**
	 * Update a transaction record
	 * @param usr
	 * @param trans
	 * @return
	 */
	public boolean updateTransaction(User usr, Transaction trans);
	
	/**
	 * Retrieve all transaction records
	 * @param usr
	 * @return
	 */
	public List<Transaction> retrieveAllTransactions(User usr);
	
	/**
	 * Retrieve transaction records by criteria
	 * @param usr
	 * @param criteria
	 * @return
	 */
	public List<Transaction> searchTransactions(User usr, Criteria criteria);
	
}
