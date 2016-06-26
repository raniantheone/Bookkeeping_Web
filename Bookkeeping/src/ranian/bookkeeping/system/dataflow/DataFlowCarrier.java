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
	
	public static final String SESSION_ATTR_NAME = "DataFlowCarrier"; 
	
	// Use these inner classes to retrieve and contain front-end input for further use by servlets
	
	public class AddOrEditTransRecordData {
		
		static final String PATH = "test01"; // TODO not decided yet
		
		private Transaction transRecordToAdd;
		
		private Transaction transRecordToEdit;
		
		public AddOrEditTransRecordData(HttpServletRequest request) {
			
			Integer transId = request.getParameter("transRecordId").isEmpty() ? 
					null : Integer.valueOf(request.getParameter("transRecordId"));
			Float transAmount = Float.valueOf(request.getParameter("transAmount"));
			Integer transTypeId = Integer.valueOf(request.getParameter("transType"));
			Integer categoryId = Integer.valueOf(request.getParameter("transCategory"));
			Integer toAccId = request.getParameter("toAcc") == null ? 
					null : Integer.valueOf(request.getParameter("toAcc"));
			Integer fromAccId = request.getParameter("fromAcc") == null ?
					null : Integer.valueOf(request.getParameter("fromAcc"));
			Timestamp transDate = new Timestamp(Long.valueOf(request.getParameter("transDate")));
			String transNote = request.getParameter("transNote");
			/*
			 * TODO validate form data here
			 */
			if( transId == null ) {
				transRecordToAdd = new Transaction(transAmount, transTypeId, toAccId, fromAccId, categoryId, transNote, transDate);
			} else {
				transRecordToEdit = new Transaction(transId, transAmount, transTypeId, toAccId, fromAccId, categoryId, transNote, transDate);
			}
			
		}

		public Transaction getTransRecordToAdd() {
			return transRecordToAdd;
		}

		public Transaction getTransRecordToEdit() {
			return transRecordToEdit;
		}
		
	}
	
	public class QueryFormData {
		
		static final String PATH = "test02"; // TODO not decided yet
		
		private Integer transType;
		
		// TODO implement this when working on editing/deleting transaction record in query form row
		// private Integer transId;
		
		public QueryFormData(HttpServletRequest request) {
			
			this.transType = Integer.valueOf(request.getParameter("transType"));
			
		}

		public Integer getTransType() {
			return transType;
		}
		
	}
	
	public AddOrEditTransRecordData addOrEditTransRecordData;
	
	public QueryFormData queryFormData;
	
	private User user;
	
	private Map<String, Object> flowResults;
	
	public DataFlowCarrier() {
		
	}
	
	public void startDataFlow(HttpServletRequest request) {
		
		String path = request.getServletPath();
		switch( path ) {
			
			case AddOrEditTransRecordData.PATH:
				this.addOrEditTransRecordData = new AddOrEditTransRecordData(request);
				break;
				
			case QueryFormData.PATH:
				this.queryFormData = new QueryFormData(request);
				break;
				
		}
		
		this.user = (User) request.getSession().getAttribute(User.SESSION_ATTR_NAME);
	}
	
	public Map<String, Object> getFlowResults() {
		return flowResults;
	}

	public void setFlowResults(Map<String, Object> flowResults) {
		this.flowResults = flowResults;
	}

	public User getUser() {
		return user;
	}
	
}
