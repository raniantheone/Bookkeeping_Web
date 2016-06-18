package ranian.bookkeeping.system.authentication.facade.mock;

import ranian.bookkeeping.system.authentication.model.User;

public class AuthenFacadeMock {
	
	private User user;
	
	public AuthenFacadeMock() {
	};
	
	public User createNewTestUser() {
		
		user = new User();
		user.setLoginAcc("Default Tester");
		user.setLoginAcc("Should be a hashed string");
		user.setUserMail("test@test.any.tw");
		user.setUserName("Yo Dummy");
		
		return user;
	}
	
}
