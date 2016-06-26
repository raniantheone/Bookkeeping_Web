package ranian.bookkeeping.system.authentication.facade;

import ranian.bookkeeping.system.authentication.model.User;

public interface IAuthenticationFacade {

	public User validateUser(String userLoginAcc, String userLoginPw);
	
}
