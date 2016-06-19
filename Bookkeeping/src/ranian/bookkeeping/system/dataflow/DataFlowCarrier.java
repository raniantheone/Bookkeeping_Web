package ranian.bookkeeping.system.dataflow;

import java.sql.Timestamp;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import ranian.bookkeeping.features.transaction.model.Transaction;
import ranian.bookkeeping.system.authentication.model.User;

/**
 * Handles data preparation from front-end to servlets, 
 * as well as processed results from servlets to front-end. 
 * 
 * @author Ranian
 *
 */
public class DataFlowCarrier {
	
	public class AddOrEditTransRecordData {
		
		static final String PATH = "";
		
		private Transaction transRecordToAdd;
		
		private Transaction transRecordToEdit;
		
		private User user;
		
		public AddOrEditTransRecordData(HttpServletRequest request) {
			
			Map<String, String[]> paramsMap = request.getParameterMap();
			
			Integer transId = Integer.valueOf(paramsMap.get("transRecordId")[0]);
			Float transAmount = Float.valueOf(paramsMap.get("transAmount")[0]);
			Integer transTypeId = Integer.valueOf(paramsMap.get("transType")[0]);
			Integer toAccId = Integer.valueOf(paramsMap.get("toAcc")[0]);
			Integer fromAccId = Integer.valueOf(paramsMap.get("fromAcc")[0]);
			Timestamp transDate = new Timestamp(Long.valueOf(paramsMap.get("transDate")[0]));
			String transNote = paramsMap.get("transNote")[0];
			/*
			 * TODO validate form data here
			 */
			
			/*
			 * construct and assign object here
			 */
		}

		public Transaction getTransRecordToAdd() {
			return transRecordToAdd;
		}

		public Transaction getTransRecordToEdit() {
			return transRecordToEdit;
		}

		public User getUser() {
			return user;
		}
		
	}
	
	public AddOrEditTransRecordData addOrEditTransRecordData;
	
	private Map<String, Object> flowResults;
	
	public DataFlowCarrier() {
		
	}
	
	public void startDataFlow(HttpServletRequest request) {
		
		String path = request.getServletPath();
		switch( path ) {
			
			case AddOrEditTransRecordData.PATH:
				this.addOrEditTransRecordData = new AddOrEditTransRecordData(request);
				break;
				
		}
		
	}
	
	public Map<String, Object> getFlowResults() {
		return flowResults;
	}

	public void setFlowResults(Map<String, Object> flowResults) {
		this.flowResults = flowResults;
	}
	
}
