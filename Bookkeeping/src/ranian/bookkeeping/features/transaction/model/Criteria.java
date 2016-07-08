package ranian.bookkeeping.features.transaction.model;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

public class Criteria {
	
	private Integer recordIdEqualsTo;
	
	private Float amountGreaterThanOrEqualsTo;
	
	private Float amountSmallerThanOrEqualsTo;
	
	private Integer typeIdEqualsTo;
	
	private Integer toAccIdEqualsTo;
	
	private Integer fromAccIdEqualsTo;
	
	private Integer categoryIdEqualsTo;
	
	private Timestamp recordTimeNewerThanOrEqualsTo;
	
	private Timestamp recordTimeOlderThanOrEqualsTo;

	public Map<String, Object> getCriteriaMap() {
		
		Map<String, Object> criteriaMap = new LinkedHashMap<String, Object>();
		
		if( recordIdEqualsTo != null ) {
			criteriaMap.put("RECORD_ID=", recordIdEqualsTo);
		}
		if( amountGreaterThanOrEqualsTo != null ) {
			criteriaMap.put("AMOUNT >=", amountGreaterThanOrEqualsTo);
		}
		if( amountSmallerThanOrEqualsTo != null ) {
			criteriaMap.put("AMOUNT <=", amountSmallerThanOrEqualsTo);
		}
		if( typeIdEqualsTo != null ) {
			criteriaMap.put("TYPE_ID =", typeIdEqualsTo);
		}
		if( toAccIdEqualsTo != null ) {
			criteriaMap.put("TO_ACC_ID =", toAccIdEqualsTo);
		}
		if( fromAccIdEqualsTo != null ) {
			criteriaMap.put("FROM_ACC_ID =",fromAccIdEqualsTo);
		}
		if( categoryIdEqualsTo != null ) {
			criteriaMap.put("CATEGORY_ID =", categoryIdEqualsTo);
		}
		if( recordTimeNewerThanOrEqualsTo != null ) {
			criteriaMap.put("RECORD_TIME >=", recordTimeNewerThanOrEqualsTo);
		}
		if( recordTimeOlderThanOrEqualsTo != null ) {
			criteriaMap.put("RECORD_TIME <=", recordTimeOlderThanOrEqualsTo);
		}
		
		return criteriaMap;
	}
	
	public void setRecordIdEqualsTo(Integer recordIdEqualsTo) {
		this.recordIdEqualsTo = recordIdEqualsTo;
	}

	public void setAmountGreaterThanOrEqualsTo(Float amountGreaterThanOrEqualsTo) {
		this.amountGreaterThanOrEqualsTo = amountGreaterThanOrEqualsTo;
	}

	public void setAmountSmallerThanOrEqualsTo(Float amountSmallerThanOrEqualsTo) {
		this.amountSmallerThanOrEqualsTo = amountSmallerThanOrEqualsTo;
	}

	public void setTypeIdEqualsTo(Integer typeIdEqualsTo) {
		this.typeIdEqualsTo = typeIdEqualsTo;
	}

	public void setToAccIdEqualsTo(Integer toAccIdEqualsTo) {
		this.toAccIdEqualsTo = toAccIdEqualsTo;
	}

	public void setFromAccIdEqualsTo(Integer fromAccIdEqualsTo) {
		this.fromAccIdEqualsTo = fromAccIdEqualsTo;
	}

	public void setCategoryIdEqualsTo(Integer categoryIdEqualsTo) {
		this.categoryIdEqualsTo = categoryIdEqualsTo;
	}

	public void setRecordTimeNewerThanOrEqualsTo(Timestamp recordTimeNewerThanOrEqualsTo) {
		this.recordTimeNewerThanOrEqualsTo = recordTimeNewerThanOrEqualsTo;
	}

	public void setRecordTimeOlderThanOrEqualsTo(Timestamp recordTimeOlderThanOrEqualsTo) {
		this.recordTimeOlderThanOrEqualsTo = recordTimeOlderThanOrEqualsTo;
	}
	
	
}
