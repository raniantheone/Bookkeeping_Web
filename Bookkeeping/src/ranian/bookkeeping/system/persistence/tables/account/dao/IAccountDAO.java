package ranian.bookkeeping.system.persistence.tables.account.dao;

import java.util.List;

import ranian.bookkeeping.system.persistence.tables.account.vo.AccountVO;

public interface IAccountDAO {

	public Boolean insertAccountByUser(AccountVO account, Integer userId);
	
	public Boolean updateAccountByUser(AccountVO account, Integer userId);
	
	public Boolean deleteAccountByUser(AccountVO account, Integer userId);
	
	public List<AccountVO> retrieveAllAccountsByUser(Integer userId);
	
}
