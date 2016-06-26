package ranian.bookkeeping.system.persistence.tables.user.vo;

public class UserVO {

	private Integer userId;
	
	private String userLoginAcc;
	
	private String userLoginPw;
	
	private String userName;
	
	private String userMail;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserLoginAcc() {
		return userLoginAcc;
	}

	public void setUserLoginAcc(String userLoginAcc) {
		this.userLoginAcc = userLoginAcc;
	}

	public String getUserLoginPw() {
		return userLoginPw;
	}

	public void setUserLoginPw(String userLoginPw) {
		this.userLoginPw = userLoginPw;
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
