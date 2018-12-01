package ranian.bookkeeping.system.dataflow.refractoring;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * The gatekeeper of system. It does following tasks:
 * 1. Try to transform front-end input into request beans for controller
 * 2. Validate request bean
 * 3. Pick the view component for response bean from controller
 * 
 * note: request bean should indicate the expected response format, 
 * i.e., what kind of view layer is expecting the response
 * 
 * @author ranian
 *
 */
public class DataFlowCarrier_New {
	
	/**
	 * Flow memo
	 * httpRequest comes in
	 * -> find a matched class of request bean according to request uri
	 * -> construct the request bean
	 * -> validate the data from request
	 * -> initialize request bean from valid data
	 * -> invoke matched method of controller according to request bean
	 * 
	 * controller returns a processed bean
	 * -> find a matched bean handler according to target(java view layer, json string, external uri...)
	 * -> invoke handler method
	 */
	
	private HttpSession session;
	
	private RequestBean requestBean;
	
	// private ResponseBean responseBean; TODO tbc
	
	public DataFlowCarrier_New() {
		
	}

	public DataFlowCarrier_New(HttpServletRequest request) {
		this.session = request.getSession();
		this.session.setAttribute("", this); // TBC
	}
	
	public RequestBean locateRequestBean(String requestUrl) {
		
		// Find the input bean class that matches the url
		return null;
	}
	
	public boolean validateRequestBean(Map<String, String[]> requestParamsMap) {
		
		// Validate parameter data
		
		// Set bean properties if validation is OK
		
		return false;
	}
	
	public String locateViewComponent() {
		// TODO tbc
		return null;
	}
}
