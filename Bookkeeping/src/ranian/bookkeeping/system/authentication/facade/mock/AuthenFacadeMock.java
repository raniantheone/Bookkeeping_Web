package ranian.bookkeeping.system.authentication.facade.mock;

import ranian.bookkeeping.system.authentication.facade.IAuthenticationFacade;
import ranian.bookkeeping.system.authentication.model.User;
import ranian.bookkeeping.system.persistence.tables.user.dao.impl.UserDAO;
import ranian.bookkeeping.system.persistence.tables.user.vo.UserVO;

public class AuthenFacadeMock implements IAuthenticationFacade{

	
	public AuthenFacadeMock() {
	}

	@Override
	public User validateUser(String userLoginAcc, String userLoginPw) {
		
		UserDAO userDao = new UserDAO();
		UserVO userVo = userDao.getUserById(1);
		User user = new User(userVo.getUserId(), 
				userVo.getUserLoginAcc(), 
				userVo.getUserLoginPw(), 
				userVo.getUserName(), 
				userVo.getUserMail());
		
		return user;
	}

	@Override
	public Boolean isValidApplicant(String userAccount, String userEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createNewUser(User applicantUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean createDefaultData(User newlyCreatedUser) {
		// TODO Auto-generated method stub
		return null;
	};
	
}
