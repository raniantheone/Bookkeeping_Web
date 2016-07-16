package ranian.bookkeeping.features.account.model;

public class Account {
	
	private Integer accountId;
	
	private Integer userId;
	
	private String accountName;
	
	private String accountDesc;
	
	public Account(Integer userId, String accountName, String accountDesc) {
		this.userId = userId;
		this.accountName = accountName;
		this.accountDesc = accountDesc;
	}
	
	public Account(Integer accountId, Integer userId, String accountName, String accountDesc) {
		this(userId, accountName, accountDesc);
		this.accountId = accountId;
	}
	
	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountDesc() {
		return accountDesc;
	}

	public void setAccountDesc(String accountDesc) {
		this.accountDesc = accountDesc;
	}
	
}
