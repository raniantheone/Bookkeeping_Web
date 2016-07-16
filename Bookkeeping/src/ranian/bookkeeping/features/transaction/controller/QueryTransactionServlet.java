package ranian.bookkeeping.features.transaction.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ranian.bookkeeping.features.transaction.facade.IFormSetupFacade;
import ranian.bookkeeping.features.transaction.facade.ITransactionFacade;
import ranian.bookkeeping.features.transaction.facade.impl.FormSetupFacadeImpl;
import ranian.bookkeeping.features.transaction.facade.impl.TransactionFacadeImpl;
import ranian.bookkeeping.features.transaction.model.Transaction;
import ranian.bookkeeping.system.dataflow.DataFlowCarrier;

/**
 * Servlet implementation class QueryTransactionServlet
 */
@WebServlet("/QueryTransactionServlet")
public class QueryTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryTransactionServlet() {
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

		HttpSession session = request.getSession();
		DataFlowCarrier dataFlowCarrier = (DataFlowCarrier) session.getAttribute(DataFlowCarrier.SESSION_ATTRIBUTE_NAME);
		
		IFormSetupFacade formSetupFacade = new FormSetupFacadeImpl();
		Map<Integer, String> transactionTypes = formSetupFacade.getTransactionTypes();
		
		ITransactionFacade transactionFacade = new TransactionFacadeImpl();
		List<Transaction> transactions = transactionFacade.retrieveAllTransactions(dataFlowCarrier.getUser());

		Map<String, Object> dataFlowResults = new HashMap<String, Object>();
		dataFlowResults.put("transactionTypes", transactionTypes);
		dataFlowResults.put("transactions", transactions);
		dataFlowCarrier.setFlowResults(dataFlowResults);
		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/QueryTransactionRecord.jsp");
		reqDispatcher.forward(request, response);
		
	}

}
