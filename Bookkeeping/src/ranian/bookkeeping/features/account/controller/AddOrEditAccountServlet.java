package ranian.bookkeeping.features.account.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ranian.bookkeeping.features.account.facade.IAccountManagementFacade;
import ranian.bookkeeping.features.account.facade.impl.AccountManagementFacade;
import ranian.bookkeeping.system.authentication.model.User;
import ranian.bookkeeping.system.dataflow.DataFlowCarrier;

/**
 * Servlet implementation class AddOrEditAccountServlet
 */
@WebServlet("/AddOrEditAccountServlet")
public class AddOrEditAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrEditAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		DataFlowCarrier dataFlowCarrier = (DataFlowCarrier) session.getAttribute(DataFlowCarrier.SESSION_ATTRIBUTE_NAME);
		User user = dataFlowCarrier.getUser();
		
		Boolean operationSuccess = false;
		IAccountManagementFacade accountFacade = new AccountManagementFacade();
		if( dataFlowCarrier.addOrEditAccountData.getIsEdit() ) {
			
			operationSuccess = accountFacade.editAccount(user, 
					dataFlowCarrier.addOrEditAccountData.getAccountToEdit());
			
		} else {
			
			operationSuccess = accountFacade.createAccount(user, 
					dataFlowCarrier.addOrEditAccountData.getAccountToAdd());
			
		}
		
		Map<String, Object> dataFlowResults = new HashMap<String, Object>();
		dataFlowResults.put("operationSuccess", operationSuccess); // null if it's a add account operation
		dataFlowCarrier.setFlowResults(dataFlowResults);

		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/queryAccounts"); // direct request to QueryAccountServlet
		reqDispatcher.forward(request, response);
	}

}
