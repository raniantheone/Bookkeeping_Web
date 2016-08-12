package ranian.bookkeeping.system.persistence.tables.user.dao;

import ranian.bookkeeping.system.persistence.tables.user.vo.UserVO;

public interface IUserDAO {

	public Boolean insertUser(UserVO user);
	
	public Boolean updateUser(UserVO user);
	
	public Boolean deleteUser(UserVO user);
	
	public UserVO getUserById(Integer userId);
	
	public UserVO getUserByLoginAccount(String userLoginAccount);
	
	public UserVO getAuthenticatedUser(String userAccount, String userPassword);
	
}
