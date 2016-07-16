package ranian.bookkeeping.system.dataflow;

import java.sql.Timestamp;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import ranian.bookkeeping.features.category.model.Category;
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
	
	public static final String SESSION_ATTRIBUTE_NAME = "DataFlowCarrier"; 
	
	// Use these inner classes to retrieve and contain front-end input for further use by servlets
	
	public class AddOrEditTransRecordData {
		
		static final String PATH = "/addOrEditTransaction";
		
		private Transaction transRecordToAdd;
		
		private Transaction transRecordToEdit;
		
		private Boolean isEdit;
		
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
				isEdit = false;
			} else {
				transRecordToEdit = new Transaction(transId, transAmount, transTypeId, toAccId, fromAccId, categoryId, transNote, transDate);
				isEdit = true;
			}
			
		}

		public Transaction getTransRecordToAdd() {
			return transRecordToAdd;
		}

		public Transaction getTransRecordToEdit() {
			return transRecordToEdit;
		}

		public Boolean isEdit() {
			return isEdit;
		}
		
	}

	public class FormSetupData {
		
		static final String PATH = "/setupAddOrEditForm";
		
		private Integer transType;
		
		private Integer transIdForEdit;
		
		private Boolean isEdit;
		
		public FormSetupData(HttpServletRequest request) {
			
			transType = request.getParameter("transType") == null ?
					null : Integer.valueOf(request.getParameter("transType"));
			
			transIdForEdit = request.getParameter("transIdForEdit") == null ?
					null : Integer.valueOf(request.getParameter("transIdForEdit"));
			
			isEdit = transIdForEdit != null;
			
		}

		public Integer getTransType() {
			return transType;
		}

		public Integer getTransIdForEdit() {
			return transIdForEdit;
		}

		public Boolean isEdit() {
			return isEdit;
		}
		
	}
	
	public class DeleteTransactionData {
		
		static final String PATH = "/delTransaction";
		
		private Integer transIdForDel;
		
		public DeleteTransactionData(HttpServletRequest request) {
			
			transIdForDel = Integer.valueOf(request.getParameter("transIdForDel"));
			
		}

		public Integer getTransIdForDel() {
			return transIdForDel;
		}
		
	}
	
	public class AddOrEditCategoryFormData {
		
		static final String PATH = "/serveAddOrEditForm";
		
		private Integer categoryIdForEdit;
		
		private Boolean isEdit;
		
		public AddOrEditCategoryFormData(HttpServletRequest request) {
			
			categoryIdForEdit = request.getParameter("categoryIdForEdit") == null ?
					null : Integer.valueOf(request.getParameter("categoryIdForEdit"));
			
			isEdit = categoryIdForEdit != null;
			
		}

		public Integer getCategoryIdForEdit() {
			return categoryIdForEdit;
		}

		public Boolean isEdit() {
			return isEdit;
		}
		
	}
	
	public class AddOrEditCategoryData {
		
		static final String PATH = "/addOrEditCategory";
		
		private String categoryName;
		
		private Category categoryForEdit;
		
		private Category categoryForAdd;
		
		private Boolean isEdit;
		
		public AddOrEditCategoryData(HttpServletRequest request) {
			
			this.categoryName = request.getParameter("categoryName");
			
			Integer categoryIdForEdit = request.getParameter("categoryIdForEdit").isEmpty() ?
					null : Integer.valueOf(request.getParameter("categoryIdForEdit"));
			
			if( categoryIdForEdit != null ) {
				
				categoryForEdit = new Category(categoryIdForEdit, user.getUserId(), categoryName);
				isEdit = true;
				
			} else {
				
				categoryForAdd = new Category(user.getUserId(), categoryName);
				isEdit = false;
				
			}
			
		}

		public String getCategoryName() {
			return categoryName;
		}

		public Category getCategoryForEdit() {
			return categoryForEdit;
		}

		public Category getCategoryForAdd() {
			return categoryForAdd;
		}

		public Boolean isEdit() {
			return isEdit;
		}
		
	}
	
	public AddOrEditTransRecordData addOrEditTransRecordData;
	
	public FormSetupData formSetupData;
	
	public DeleteTransactionData deleteTransactionData; 
	
	public AddOrEditCategoryFormData addOrEditCategoryFormData;
	
	public AddOrEditCategoryData addOrEditCategoryData;
	
	private User user;
	
	private Map<String, Object> flowResults;
	
	public DataFlowCarrier() {
		
	}
	
	public void startDataFlow(HttpServletRequest request) {
		
		this.user = (User) request.getSession().getAttribute(User.SESSION_ATTR_NAME);
		
		String path = request.getServletPath();
		switch( path ) {
			
			case AddOrEditTransRecordData.PATH:
				this.addOrEditTransRecordData = new AddOrEditTransRecordData(request);
				break;
				
			case FormSetupData.PATH:
				this.formSetupData = new FormSetupData(request);
				break;
			
			case DeleteTransactionData.PATH:
				this.deleteTransactionData = new DeleteTransactionData(request);
				break;
				
			case AddOrEditCategoryFormData.PATH:
				this.addOrEditCategoryFormData = new AddOrEditCategoryFormData(request);
				break;
				
			case AddOrEditCategoryData.PATH:
				this.addOrEditCategoryData = new AddOrEditCategoryData(request);
				break;
				
		}
		
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
