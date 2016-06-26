package ranian.bookkeeping.system.persistence.tables.category.vo;

public class CategoryVO {

	private Integer categoryId;
	
	private Integer userId;
	
	private String categoryName;
	
	private Boolean categoryDel;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Boolean getCategoryDel() {
		return categoryDel;
	}

	public void setCategoryDel(Boolean categoryDel) {
		this.categoryDel = categoryDel;
	}
	
}
