package ranian.bookkeeping.features.transaction.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ranian.bookkeeping.features.transaction.facade.ITransactionFacade;
import ranian.bookkeeping.features.transaction.facade.impl.TransactionFacadeImpl;
import ranian.bookkeeping.features.transaction.model.Transaction;
import ranian.bookkeeping.system.authentication.facade.mock.AuthenFacadeMock;
import ranian.bookkeeping.system.authentication.model.User;
import ranian.bookkeeping.system.dataflow.DataFlowCarrier;

/**
 * Servlet implementation class NewTransactionServlet
 */
@WebServlet("/NewTransactionServlet")
public class NewOrEditTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewOrEditTransactionServlet() {
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
		
		HttpSession session = request.getSession();
		DataFlowCarrier dataFlowCarrier = (DataFlowCarrier) session.getAttribute(DataFlowCarrier.SESSION_ATTRIBUTE_NAME);
		
		Boolean operationSuccess = false;
		ITransactionFacade transFacade = new TransactionFacadeImpl();
		if( dataFlowCarrier.addOrEditTransRecordData.isEdit() ) {
			
			operationSuccess= transFacade.updateTransaction(dataFlowCarrier.getUser(), 
					dataFlowCarrier.addOrEditTransRecordData.getTransRecordToEdit());
			
		} else {
			
			operationSuccess = transFacade.createTransaction(dataFlowCarrier.getUser(), 
					dataFlowCarrier.addOrEditTransRecordData.getTransRecordToAdd());
			
		}
		
		Map<String, Object> dataFlowResults = new HashMap<String, Object>();
		dataFlowResults.put("operationSuccess", operationSuccess);
		dataFlowResults.put("nextActionUrl", "queryTransactionRecords");
		dataFlowResults.put("nextActionDescription", "Back to transaction management");
		dataFlowCarrier.setFlowResults(dataFlowResults);
		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/OperationResult.jsp");
		reqDispatcher.forward(request, response);

	}

}
