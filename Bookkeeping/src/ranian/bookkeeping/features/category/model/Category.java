package ranian.bookkeeping.features.category.model;

public class Category {

	private Integer categoryId;
	
	private Integer userId;
	
	private String categoryName;
	
	private Boolean hasTransRecord;
	
	public Category(Integer userId, String categoryName) {
		this.userId = userId;
		this.categoryName = categoryName;
	}
	
	public Category(Integer categoryId, Integer userId, String categoryName) {
		this(userId, categoryName);
		this.categoryId = categoryId;
	}

	public Boolean getHasTransRecord() {
		return hasTransRecord;
	}

	public void setHasTransRecord(Boolean hasTransRecord) {
		this.hasTransRecord = hasTransRecord;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getCategoryName() {
		return categoryName;
	}
	
}
