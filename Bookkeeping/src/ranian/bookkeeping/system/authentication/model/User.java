package ranian.bookkeeping.system.authentication.model;

public class User {
	
	public static final String SESSION_ATTR_NAME = "currentUser";
	
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
	private String loginPw;
	
	/**
	 * Display name of the user
	 */
	private String userName;
	
	/**
	 * Contact email of the user
	 */
	private String userMail;

	public User(String loginAcc, String loginPw, String userName, String userMail) {
		this.loginAcc = loginAcc;
		this.loginPw = loginPw;
		this.userName = userName;
		this.userMail = userMail;
	}
	
	public User(Integer userId, String loginAcc, String loginPw, String userName, String userMail) {
		this(loginAcc, loginPw, userName, userMail);
		this.userId = userId;
	}

	public static String getSessionAttrName() {
		return SESSION_ATTR_NAME;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getLoginAcc() {
		return loginAcc;
	}

	public String getLoginPw() {
		return loginPw;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserMail() {
		return userMail;
	}
	
}
