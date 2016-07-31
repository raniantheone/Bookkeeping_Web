package ranian.bookkeeping.features.account.facade.impl;

import java.util.ArrayList;
import java.util.List;

import ranian.bookkeeping.features.account.model.Account;
import ranian.bookkeeping.features.account.facade.IAccountManagementFacade;
import ranian.bookkeeping.system.authentication.model.User;
import ranian.bookkeeping.system.persistence.tables.account.dao.IAccountDAO;
import ranian.bookkeeping.system.persistence.tables.account.dao.impl.AccountDAO;
import ranian.bookkeeping.system.persistence.tables.account.vo.AccountVO;

public class AccountManagementFacade implements IAccountManagementFacade {

	@Override
	public List<Account> getAccountsByUser(User user) {
		
		List<Account> accounts = new ArrayList<Account>();
		
		IAccountDAO accountDao = new AccountDAO();
		List<AccountVO> accountVos = accountDao.retrieveAllAccountsByUser(user.getUserId());
		for( AccountVO accountVo : accountVos ) {
			accounts.add(new Account(accountVo.getAccountId(), 
					accountVo.getUserId(), 
					accountVo.getAccountName(),
					accountVo.getAccountDesc()));
		}
		
		return accounts;
	}

	@Override
	public Boolean createAccount(User user, Account account) {
		
		Boolean isSuccess = false;

		AccountVO accountVo = new AccountVO();
		accountVo.setAccountName(account.getAccountName());
		accountVo.setAccountDesc(account.getAccountDesc());
		accountVo.setUserId(user.getUserId());
		IAccountDAO accountDao = new AccountDAO();
		isSuccess = accountDao.insertAccountByUser(accountVo, user.getUserId());
		
		return isSuccess;
	}

	@Override
	public Boolean editAccount(User user, Account account) {
		
		Boolean isSuccess = false;
		
		IAccountDAO accountDao = new AccountDAO();
		AccountVO accountVo = new AccountVO();
		accountVo.setAccountId(account.getAccountId());
		accountVo.setAccountName(account.getAccountName());
		accountVo.setAccountDesc(account.getAccountDesc());
		accountVo.setUserId(user.getUserId());
		
		isSuccess = accountDao.updateAccountByUser(accountVo, user.getUserId());
		
		return isSuccess;
	}

	@Override
	public Boolean deleteAccount(User user, Integer accoutnIdForDel) {
		
		Boolean isSuccess = false;
		
		IAccountDAO accountDao = new AccountDAO();
		isSuccess = accountDao.deleteAccountByUser(accoutnIdForDel, user.getUserId());
		
		return isSuccess;
	}

	@Override
	public Account getAccountForEdit(User user, Integer accountId) {
		
		Account accountForEdit = null;
		
		IAccountDAO accountDao = new AccountDAO();
		AccountVO accountVo = accountDao.retrieveAccountById(user.getUserId(), accountId);
		accountForEdit = new Account(accountVo.getAccountId(), 
				accountVo.getUserId(), 
				accountVo.getAccountName(), 
				accountVo.getAccountDesc());
		
		return accountForEdit;
	}


}
