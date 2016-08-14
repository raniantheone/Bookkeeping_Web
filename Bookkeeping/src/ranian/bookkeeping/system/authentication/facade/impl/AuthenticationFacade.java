package ranian.bookkeeping.system.authentication.facade.impl;

import ranian.bookkeeping.system.authentication.facade.IAuthenticationFacade;
import ranian.bookkeeping.system.authentication.model.User;
import ranian.bookkeeping.system.persistence.tables.user.dao.impl.UserDAO;
import ranian.bookkeeping.system.persistence.tables.user.vo.UserVO;

public class AuthenticationFacade implements IAuthenticationFacade {

	@Override
	public User validateUser(String userLoginAcc, String userLoginPw) {
		
		User user = null;
		
		UserDAO userDao = new UserDAO();
		UserVO userVo = userDao.getAuthenticatedUser(userLoginAcc, userLoginPw);
		
		if( userVo != null ) {
			user = new User(userVo.getUserId(), 
					userVo.getUserLoginAcc(), 
					userVo.getUserLoginPw(), 
					userVo.getUserName(), 
					userVo.getUserMail());
		}

		return user;
	}
	
}
