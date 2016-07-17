package ranian.bookkeeping.features.account.facade;

import java.util.List;

import ranian.bookkeeping.features.account.model.Account;
import ranian.bookkeeping.system.authentication.model.User;

public interface IAccountManagementFacade {

	public List<Account> getAccountsByUser(User user);
	
	public Boolean createAccount(User user, Account account);
	
	public Boolean editAccount(User user, Account account);
	
	public Boolean deleteAccount(User user, Integer accoutnIdForDel);
	
	public Account getAccountForEdit(User user, Integer accountId);
	
}
