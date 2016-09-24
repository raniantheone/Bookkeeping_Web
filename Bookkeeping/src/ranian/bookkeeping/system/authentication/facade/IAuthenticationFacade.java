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
	
	/**
	 * Check if the data provided by this applicant is valid
	 * @param userAccount
	 * @param userEmail
	 * @return true if both userAccount and userEmail are unique in system
	 */
	public Boolean isValidApplicant(String userAccount, String userEmail);
	
	/**
	 * Create a legitimate user for this applicant
	 * @param applicantUser
	 * @return A newly created user
	 */
	public User createNewUser(User applicantUser);
	
	/**
	 * Create default bookkeeping account and category for the newly created user
	 * @param newlyCreatedUser
	 * @return true if operation success
	 */
	public Boolean createDefaultData(User newlyCreatedUser);
}
