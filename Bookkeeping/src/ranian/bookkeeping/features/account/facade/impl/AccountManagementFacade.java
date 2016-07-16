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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean editAccount(User user, Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteAccount(User user, Integer accoutnIdForDel) {
		// TODO Auto-generated method stub
		return null;
	}


}
