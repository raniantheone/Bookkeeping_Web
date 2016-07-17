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
import ranian.bookkeeping.features.account.model.Account;
import ranian.bookkeeping.system.authentication.model.User;
import ranian.bookkeeping.system.dataflow.DataFlowCarrier;

/**
 * Servlet implementation class ServeAddOrEditAccountFormServlet
 */
@WebServlet("/ServeAddOrEditAccountFormServlet")
public class ServeAddOrEditAccountFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeAddOrEditAccountFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Get dataflowcarrier
		HttpSession session = request.getSession();
		DataFlowCarrier dataFlowCarrier = (DataFlowCarrier) session.getAttribute(DataFlowCarrier.SESSION_ATTRIBUTE_NAME);
		
		// TODO If it's an edit account operation, get the account for update later; else do nothing
		Account accountForEdit = null;
		if( dataFlowCarrier.addOrEditAccountFormData.isEdit() ) {
			
			User user = dataFlowCarrier.getUser();
			Integer accountIdForEdit = dataFlowCarrier.addOrEditAccountFormData.getAccountIdForEdit();
			
			IAccountManagementFacade accountFacade = new AccountManagementFacade();
			accountForEdit = accountFacade.getAccountForEdit(user, accountIdForEdit);
			
		}
		
		// TODO Send operation result to front-end
		Map<String, Object> dataFlowResults = new HashMap<String, Object>();
		dataFlowResults.put("accountForEdit", accountForEdit); // null if it's a add account operation
		dataFlowCarrier.setFlowResults(dataFlowResults);

		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/account/AddOrEditAccount.jsp");
		reqDispatcher.forward(request, response);
		
	}

}
