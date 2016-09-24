package ranian.bookkeeping.system.authentication.facade.impl;

import ranian.bookkeeping.system.authentication.facade.IAuthenticationFacade;
import ranian.bookkeeping.system.authentication.model.User;
import ranian.bookkeeping.system.persistence.tables.account.dao.impl.AccountDAO;
import ranian.bookkeeping.system.persistence.tables.account.vo.AccountVO;
import ranian.bookkeeping.system.persistence.tables.category.dao.impl.CategoryDAO;
import ranian.bookkeeping.system.persistence.tables.category.vo.CategoryVO;
import ranian.bookkeeping.system.persistence.tables.user.dao.impl.UserDAO;
import ranian.bookkeeping.system.persistence.tables.user.vo.UserVO;
import ranian.bookkeeping.system.utilities.EncryptionUtility;

public class AuthenticationFacade implements IAuthenticationFacade {
	
	UserDAO userDao;
	
	public AuthenticationFacade() {
		this.userDao = new UserDAO();
	}
	
	@Override
	public User validateUser(String userLoginAcc, String userLoginPw) {
		
		User user = null;
		
		this.userDao = new UserDAO();
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

	@Override
	public Boolean isValidApplicant(String userAccount, String userEmail) {
		
		// Examine if the userAccount and userEmail already exist in system
		Boolean isValid = false;
		Integer existedCount = this.userDao.queryExistingAccountAndEmail(userAccount, userEmail);
		isValid = existedCount == 0;
		
		return isValid;
	}

	@Override
	public User createNewUser(User applicantUser) {

		// Create a new user for this applicant
		UserVO userVo = new UserVO();
		userVo.setUserLoginAcc(applicantUser.getLoginAcc());
		userVo.setUserLoginPw(EncryptionUtility.encodePassword(applicantUser.getLoginPw()));
		userVo.setUserName(applicantUser.getUserName());
		userVo.setUserMail(applicantUser.getUserMail());
		Integer userId = this.userDao.insertUser(userVo);
		
		// Prepare the newly created user for return
		UserVO newlyCreatedUserVo = this.userDao.getUserById(userId);
		User newlyCreatedUser = new User(newlyCreatedUserVo.getUserId(), 
				newlyCreatedUserVo.getUserLoginAcc(), 
				newlyCreatedUserVo.getUserLoginPw(), 
				newlyCreatedUserVo.getUserName(), 
				newlyCreatedUserVo.getUserMail());
		
		return newlyCreatedUser;
	}

	@Override
	public Boolean createDefaultData(User newlyCreatedUser) {
		
		Boolean isSuccess = true;
		Integer userId = newlyCreatedUser.getUserId();
		
		// Create default account and category for the new user
		AccountVO accountVo= new AccountVO();
		accountVo.setAccountName("default Account"); // TODO extract it to config file such as properties
		accountVo.setAccountDesc("Please change this as anything fits your need"); // TODO extract it to config file such as properties
		accountVo.setUserId(userId); // TODO refractor this duplicated parameter
		AccountDAO accountDao = new AccountDAO();
		isSuccess = accountDao.insertAccountByUser(accountVo, userId);
		
		CategoryDAO categoryDao = new CategoryDAO();
		isSuccess = isSuccess && categoryDao.insertCategoryByUser("default Category", userId); // TODO extract it to config file such as properties, as well as VO DAO code structure consistency
		
		return isSuccess;
	}
	
}
