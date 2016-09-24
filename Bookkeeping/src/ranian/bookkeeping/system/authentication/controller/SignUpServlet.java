package ranian.bookkeeping.system.authentication.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ranian.bookkeeping.system.authentication.facade.IAuthenticationFacade;
import ranian.bookkeeping.system.authentication.facade.impl.AuthenticationFacade;
import ranian.bookkeeping.system.authentication.model.User;
import ranian.bookkeeping.system.dataflow.DataFlowCarrier;

/**
 * Servlet implementation class SignUpServlet
 */
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataFlowCarrier dataFlowCarrier = DataFlowCarrier.GetCurrentDataFlowCarrier(request);
		User applicantUser = dataFlowCarrier.signUpFormData.getSignUpUser();
		
		Boolean operationSuccess = false;
		
		// Check applicant data
		String applicantAccount = applicantUser.getLoginAcc();
		String applicantEmail = applicantUser.getUserMail();
		User newlyCreatedUser = null;
		IAuthenticationFacade authFacade = new AuthenticationFacade();
		if( authFacade.isValidApplicant(applicantAccount, applicantEmail) ) {

			// Create new user
			newlyCreatedUser = authFacade.createNewUser(applicantUser);
			
			// Create default account and category
			authFacade.createDefaultData(newlyCreatedUser);
			
			// Treat the newly created user as a logged-in user
			request.getSession().setAttribute(User.SESSION_ATTR_NAME, newlyCreatedUser);
			
			operationSuccess = true;
		}
		
		Map<String, Object> dataFlowResults = new HashMap<String, Object>();
		dataFlowResults.put("operationSuccess", operationSuccess);
		dataFlowResults.put("nextActionUrl", "queryTransactionRecords");
		dataFlowResults.put("nextActionDescription", "Start using bookkeeping service");
		dataFlowCarrier.setFlowResults(dataFlowResults);

		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/OperationResult.jsp"); 
		reqDispatcher.forward(request, response);
	}

}
