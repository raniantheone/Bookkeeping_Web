package ranian.bookkeeping.system.persistence.transaction.vo;

import java.sql.Timestamp;

public class TransactionRecordVO {

	/*
	| RECORD_ID   | int(10) unsigned | NO   | PRI | NULL    | auto_increment |
	| AMOUNT      | decimal(11,2)    | NO   |     | NULL    |                |
	| TYPE_ID     | int(10) unsigned | NO   | MUL | NULL    |                |
	| TO_ACC_ID   | int(10) unsigned | YES  | MUL | NULL    |                |
	| FROM_ACC_ID | int(10) unsigned | YES  | MUL | NULL    |                |
	| CATEGORY_ID | int(10) unsigned | NO   | MUL | NULL    |                |
	| NOTE        | varchar(512)     | YES  |     | NULL    |                |
	| RECORD_TIME | datetime         | NO   |     | NULL    |                |
	| RECORD_DEL  | tinyint(1)       | YES  |     | NULL    |                |
	 */
	
	private Integer recordId;
	
	private Float amount;
	
	private Integer typeId;
	
	private Integer toAcc;
	
	private Integer fromAcc;
	
	private Integer categoryId;
	
	private String note;
	
	private Timestamp recordTime;
	
	private Integer recordDel;

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

	public Integer getRecordDel() {
		return recordDel;
	}

	public void setRecordDel(Integer recordDel) {
		this.recordDel = recordDel;
	}
	
}
