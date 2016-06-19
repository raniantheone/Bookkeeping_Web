package ranian.bookkeeping.features.transaction.model;

import java.sql.Timestamp;

public class Transaction {
	/*
	RECORD_ID		INT UNSIGNED
	AMOUNT		DECIMAL(11,2)
	TYPE_ID		INT UNSIGNED
	TO_ACC_ID	Y	INT UNSIGNED
	FROM_ACC_ID	Y	INT UNSIGNED
	CATEGORY_ID		INT UNSIGNED
	NOTE	Y	VARCHAR(512)
	RECORD_TIME		DATETIME
	RECORD_DEL	Y	TINYINT(1)
	*/
	
	private Integer transRecordId;
	
	private Float transAmount;
	
	private Integer transType;
	
	private Integer toAccId;
	
	private Integer fromAccId;
	
	private Integer transCategory;
	
	private String transNote;
	
	private Timestamp transRecordTime;
	
	private Boolean delTransRecord;
	
	public Transaction(Integer recordId ,Float amount, Integer type, Integer toAcc, 
			Integer fromAcc, Integer category, String note, Timestamp time) {
		this(amount, type, toAcc, fromAcc, category, note, time);
		this.transRecordId = recordId;
	}
	
	public Transaction(Float amount, Integer type, Integer toAcc, 
			Integer fromAcc, Integer category, String note, Timestamp time) {
		this.transAmount = amount;
		this.transType = type;
		this.toAccId = toAcc;
		this.fromAccId = fromAcc;
		this.transCategory = category;
		this.transNote = note;
		this.transRecordTime = time;
	}
	
	public Integer getTransRecordId() {
		return transRecordId;
	}

	public void setTransRecordId(Integer transRecordId) {
		this.transRecordId = transRecordId;
	}

	public Float getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(Float transAmount) {
		this.transAmount = transAmount;
	}

	public Integer getTransType() {
		return transType;
	}

	public void setTransType(Integer transType) {
		this.transType = transType;
	}

	public Integer getToAccId() {
		return toAccId;
	}

	public void setToAccId(Integer toAccId) {
		this.toAccId = toAccId;
	}

	public Integer getFromAccId() {
		return fromAccId;
	}

	public void setFromAccId(Integer fromAccId) {
		this.fromAccId = fromAccId;
	}

	public Integer getTransCategory() {
		return transCategory;
	}

	public void setTransCategory(Integer transCategory) {
		this.transCategory = transCategory;
	}

	public String getTransNote() {
		return transNote;
	}

	public void setTransNote(String transNote) {
		this.transNote = transNote;
	}

	public Timestamp getTransRecordTime() {
		return transRecordTime;
	}

	public void setTransRecordTime(Timestamp transRecordTime) {
		this.transRecordTime = transRecordTime;
	}

	public Boolean getDelTransRecord() {
		return delTransRecord;
	}

	public void setDelTransRecord(Boolean delTransRecord) {
		this.delTransRecord = delTransRecord;
	}
	
}
