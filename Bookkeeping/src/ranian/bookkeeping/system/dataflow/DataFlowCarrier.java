package ranian.bookkeeping.system.dataflow;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import ranian.bookkeeping.features.account.model.Account;
import ranian.bookkeeping.features.category.model.Category;
import ranian.bookkeeping.features.transaction.model.Criteria;
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
	
	public class AddOrEditAccountFormData {
		
		static final String PATH = "/serveAddOrEditAccountForm";
		
		private Integer accountIdForEdit;
		
		private Boolean isEdit; 
		
		public AddOrEditAccountFormData(HttpServletRequest request) {
			
			accountIdForEdit = request.getParameter("accountIdForEdit") == null ?
					null : Integer.valueOf(request.getParameter("accountIdForEdit"));
			
			isEdit = accountIdForEdit != null;
			
		}

		public Integer getAccountIdForEdit() {
			return accountIdForEdit;
		}

		public Boolean isEdit() {
			return isEdit;
		}
		
	}
	
	public class AddOrEditAccountData {
		
		static final String PATH = "/addOrEditAccount";
		
		private String accountName;
		
		private String accountDescription;
		
		private Integer accountIdToEdit;
		
		private Boolean isEdit;
		
		private Account accountToEdit;
		
		private Account accountToAdd;		
		
		public AddOrEditAccountData(HttpServletRequest request) {
			
			accountName = request.getParameter("accountName");
			
			accountDescription = request.getParameter("accountDescription");
			
			accountIdToEdit = request.getParameter("accountIdToEdit").isEmpty() ?
					null : Integer.valueOf(request.getParameter("accountIdToEdit"));
			
			isEdit = accountIdToEdit != null;
			
			if( isEdit ) {
				
				accountToEdit = new Account(accountIdToEdit, user.getUserId(), accountName, accountDescription);
				
			} else {
				
				accountToAdd = new Account(user.getUserId(), accountName, accountDescription);
				
			}
			
		}

		public String getAccountName() {
			return accountName;
		}

		public String getAccountDescription() {
			return accountDescription;
		}

		public Integer getAccountIdToEdit() {
			return accountIdToEdit;
		}

		public Boolean getIsEdit() {
			return isEdit;
		}

		public Account getAccountToEdit() {
			return accountToEdit;
		}

		public Account getAccountToAdd() {
			return accountToAdd;
		}
		
	}
	
	public class QueryTransactionData {
		
		static final String PATH = "/queryTransactionRecords";
		
		private Criteria criteria;
		
		private Boolean isDefaultInit;
		
		Boolean isPageDataRequest;
		
		public QueryTransactionData(HttpServletRequest request) {
			
			isPageDataRequest = request.getParameter("page") != null;
			isDefaultInit = request.getParameterMap().size() == 0;
			
			// TODO validation
			if( !(isPageDataRequest || isDefaultInit) ) {
				
				criteria = new Criteria();
				
				if( !request.getParameter("criteria_start_date").isEmpty() ) {
				
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					try {
						long millis = df.parse(request.getParameter("criteria_start_date")).getTime();
						criteria.setRecordTimeNewerThanOrEqualsTo(new Timestamp(millis));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				if( !request.getParameter("criteria_end_date").isEmpty() ) {
				
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					try {
						long millis = df.parse(request.getParameter("criteria_end_date")).getTime();
						criteria.setRecordTimeOlderThanOrEqualsTo(new Timestamp(millis));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
				}
				
				if( !request.getParameter("criteria_trans_type").isEmpty() ) {
					
					int typeId = Integer.valueOf(request.getParameter("criteria_trans_type"));
					criteria.setTypeIdEqualsTo(typeId);
					
				}
				
				if( !request.getParameter("criteria_category").isEmpty() ) {
					
					int categoryId = Integer.valueOf(request.getParameter("criteria_category"));
					criteria.setCategoryIdEqualsTo(categoryId);
					
				}
				
				if( !request.getParameter("criteria_from_acc").isEmpty() ) {
					
					int fromAccId = Integer.valueOf(request.getParameter("criteria_from_acc"));
					criteria.setFromAccIdEqualsTo(fromAccId);
					
				}
				
				if( !request.getParameter("criteria_to_acc").isEmpty() ) {
					
					int toAccId = Integer.valueOf(request.getParameter("criteria_to_acc"));
					criteria.setToAccIdEqualsTo(toAccId);
					
				}
				
			}
			
		}

		public Criteria getCriteria() {
			return criteria;
		}

		public Boolean isDefaultInit() {
			return isDefaultInit;
		}
		
	}
	
	public class DeleteCategoryData {
		
		static final String PATH = "/deleteCategory";
		
		private Integer categoryIdForDel;
		
		public DeleteCategoryData(HttpServletRequest request) {
			
			categoryIdForDel = Integer.valueOf(request.getParameter("categoryIdForDel"));
			
		}

		public Integer getCategoryIdForDel() {
			return categoryIdForDel;
		}
		
	}
	
	public class DeleteAccountData {
		
		static final String PATH = "/deleteAccount"; // TODO
		
		private Integer accountIdForEdit;
		
		public DeleteAccountData(HttpServletRequest request) {
			
			accountIdForEdit = Integer.valueOf(request.getParameter("accountIdForEdit"));
			
		}

		public Integer getAccountIdForEdit() {
			return accountIdForEdit;
		}
		
	}
	
	public AddOrEditTransRecordData addOrEditTransRecordData;
	
	public FormSetupData formSetupData;
	
	public DeleteTransactionData deleteTransactionData; 
	
	public AddOrEditCategoryFormData addOrEditCategoryFormData;
	
	public AddOrEditCategoryData addOrEditCategoryData;
	
	public AddOrEditAccountFormData addOrEditAccountFormData;
	
	public AddOrEditAccountData addOrEditAccountData;
	
	public QueryTransactionData queryTransactionData;
	
	public DeleteCategoryData deleteCategoryData;
	
	public DeleteAccountData deleteAccountData;
	
	private User user;
	
	private Map<String, Object> flowResults;
	
	public static DataFlowCarrier GetCurrentDataFlowCarrier(HttpServletRequest request) {
		return (DataFlowCarrier) request.getSession().getAttribute(DataFlowCarrier.SESSION_ATTRIBUTE_NAME);
	}
	
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
				
			case AddOrEditAccountFormData.PATH:
				this.addOrEditAccountFormData = new AddOrEditAccountFormData(request);
				break;
				
			case AddOrEditAccountData.PATH:
				this.addOrEditAccountData = new AddOrEditAccountData(request);
				break;
				
			case QueryTransactionData.PATH:
				this.queryTransactionData = new QueryTransactionData(request);
				break;
				
			case DeleteCategoryData.PATH:
				this.deleteCategoryData = new DeleteCategoryData(request);
				break;
				
			case DeleteAccountData.PATH:
				this.deleteAccountData = new DeleteAccountData(request);
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
