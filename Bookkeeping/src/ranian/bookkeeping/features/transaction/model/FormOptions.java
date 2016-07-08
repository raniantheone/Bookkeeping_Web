package ranian.bookkeeping.features.transaction.model;

import java.util.Map;

public class FormOptions {

	Map<Integer, String> transTypes;
	
	Map<Integer, String> userAccounts;
	
	Map<Integer, String> transCategories;
	
	Map<Long, String> dateRange;

	public Map<Integer, String> getTransTypes() {
		return transTypes;
	}

	public void setTransTypes(Map<Integer, String> transTypes) {
		this.transTypes = transTypes;
	}

	public Map<Integer, String> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(Map<Integer, String> userAccounts) {
		this.userAccounts = userAccounts;
	}

	public Map<Integer, String> getTransCategories() {
		return transCategories;
	}

	public void setTransCategories(Map<Integer, String> transCategories) {
		this.transCategories = transCategories;
	}

	public Map<Long, String> getDateRange() {
		return dateRange;
	}

	public void setDateRange(Map<Long, String> dateRange) {
		this.dateRange = dateRange;
	}
	
}
