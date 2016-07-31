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

import ranian.bookkeeping.features.account.facade.IAccountManagementFacade;
import ranian.bookkeeping.features.account.facade.impl.AccountManagementFacade;
import ranian.bookkeeping.system.dataflow.DataFlowCarrier;

/**
 * Servlet implementation class DeleteAccountServlet
 */
@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAccountServlet() {
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
		
		Boolean operationSuccess = false;
		IAccountManagementFacade accountFacade = new AccountManagementFacade();
		operationSuccess = accountFacade.deleteAccount(dataFlowCarrier.getUser(), 
				dataFlowCarrier.deleteAccountData.getAccountIdForEdit());
		
		Map<String, Object> dataFlowResults = new HashMap<String, Object>();
		dataFlowResults.put("operationSuccess", operationSuccess);
		dataFlowResults.put("nextActionUrl", "queryAccounts");
		dataFlowResults.put("nextActionDescription", "Back to account management");
		dataFlowCarrier.setFlowResults(dataFlowResults);

		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/OperationResult.jsp"); 
		reqDispatcher.forward(request, response);
	}

}
