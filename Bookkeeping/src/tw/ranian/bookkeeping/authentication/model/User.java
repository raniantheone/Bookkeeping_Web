package tw.ranian.bookkeeping.authentication.model;

public class User {
	
	/**
	 * Unique identifier of the user
	 */
	private Integer userId;
	
	/**
	 * Login account of this user, case sensitive
	 */
	private String loginAcc;
	
	/**
	 * Login password of this user, case sensitive, hashed
	 */
	private String lgoinPw;
	
	/**
	 * Display name of the user
	 */
	private String userName;
	
	/**
	 * Contact email of the user
	 */
	private String userMail;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLoginAcc() {
		return loginAcc;
	}

	public void setLoginAcc(String loginAcc) {
		this.loginAcc = loginAcc;
	}

	public String getLgoinPw() {
		return lgoinPw;
	}

	public void setLgoinPw(String lgoinPw) {
		this.lgoinPw = lgoinPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	
}
