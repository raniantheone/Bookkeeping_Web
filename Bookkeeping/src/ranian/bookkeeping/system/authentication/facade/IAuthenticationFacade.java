package ranian.bookkeeping.system.authentication.facade;

import ranian.bookkeeping.system.authentication.model.User;

public interface IAuthenticationFacade {
	
	/**
	 * Attempt to validate current user via given combination of account and password
	 * @param userLoginAcc
	 * @param userLoginPw
	 * @return A validated user if given combination is matched, else return null
	 */
	public User validateUser(String userLoginAcc, String userLoginPw);
	
}
