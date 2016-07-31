package ranian.bookkeeping.system.persistence.tables.account.dao;

import java.util.List;

import ranian.bookkeeping.system.persistence.tables.account.vo.AccountVO;

public interface IAccountDAO {

	public Boolean insertAccountByUser(AccountVO account, Integer userId);
	
	public Boolean updateAccountByUser(AccountVO account, Integer userId);
	
	public Boolean deleteAccountByUser(Integer accountId, Integer userId);
	
	public List<AccountVO> retrieveAllAccountsByUser(Integer userId);
	
	public AccountVO retrieveAccountById(Integer userId, Integer accountId);
	
}
