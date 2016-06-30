package ranian.bookkeeping.system.persistence.tables.transaction.vo;

import java.sql.Timestamp;

public class TransactionRecordVO {

	private Integer recordId;
	
	private Float amount;
	
	private Integer typeId;
	
	private Integer toAcc;
	
	private Integer fromAcc;
	
	private Integer categoryId;
	
	private String note;
	
	private Timestamp recordTime;
	
	private Boolean recordDel;
	
	private Integer userId;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getToAcc() {
		return toAcc;
	}

	public void setToAcc(Integer toAcc) {
		this.toAcc = toAcc;
	}

	public Integer getFromAcc() {
		return fromAcc;
	}

	public void setFromAcc(Integer fromAcc) {
		this.fromAcc = fromAcc;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Timestamp getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Timestamp recordTime) {
		this.recordTime = recordTime;
	}

	public Boolean getRecordDel() {
		return recordDel;
	}

	public void setRecordDel(Boolean recordDel) {
		this.recordDel = recordDel;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
