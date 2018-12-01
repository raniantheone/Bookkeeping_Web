package ranian.bookkeeping.system.dataflow.refractoring;

import java.util.Map;

/**
 * Inherited by every front-end input bean for controller.
 * 
 * @author ranian
 *
 */
public abstract class RequestBean {
	
	public class ValidationResult {
		
		private boolean isSuccess;
		
		private Map<String, String> errorMsgMap;
		
		public boolean isSuccess() {
			return this.isSuccess;
		};
		
		public Map<String, String> getErrorMsgMap() {
			return this.errorMsgMap;
		};
		
	}
	
	private ValidationResult validationResult;
	
	public RequestBean() {
		
	}
	
	/**
	 * Perform data format validation on input fields
	 * @param requestParamsMap
	 * @return
	 */
	public abstract ValidationResult validateDataFormat(Map<String, String[]> requestParamsMap);
	
	/**
	 * Set bean properties with the validated parameters
	 * @param requestParamsMap
	 */
	public abstract void configureBean(Map<String, String[]> requestParamsMap);
	
	public ValidationResult getValidationResult() {
		return this.validationResult;
	};
}
