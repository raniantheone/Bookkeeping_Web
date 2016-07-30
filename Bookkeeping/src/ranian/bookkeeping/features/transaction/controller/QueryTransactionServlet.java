package ranian.bookkeeping.features.transaction.controller;

import java.io.IOException;
import java.util.Calendar;
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
import ranian.bookkeeping.features.transaction.model.FormOptions;
import ranian.bookkeeping.features.transaction.model.Transaction;
import ranian.bookkeeping.system.dataflow.DataFlowCarrier;
import ranian.bookkeeping.system.resultpaging.PagedData;
import ranian.bookkeeping.system.resultpaging.impl.PageUtil;

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
		
		// TODO temporarily test paging via get request
		
		DataFlowCarrier dataFlowCarrier = DataFlowCarrier.GetCurrentDataFlowCarrier(request);
		
		Integer pageNum = request.getParameter("page") == null ?
				null : Integer.valueOf(request.getParameter("page"));
		
		if( pageNum == null ) {
			
			doPost(request, response);
			
		} else{
		
			PagedData pagedData = PageUtil.getInstance().retvPagedData(pageNum, request);
			
			IFormSetupFacade formSetupFacade = new FormSetupFacadeImpl();
			FormOptions formOpts = formSetupFacade.getAddOrEditFormOptionsByUser(dataFlowCarrier.getUser(), Calendar.getInstance());
			
			Map<String, Object> dataFlowResults = new HashMap<String, Object>();
			dataFlowResults.put("formOpts", formOpts);
			dataFlowResults.put("pagedData", pagedData);
			dataFlowCarrier.setFlowResults(dataFlowResults);
			
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/transaction/QueryTransactionRecord.jsp");
			reqDispatcher.forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		DataFlowCarrier dataFlowCarrier = (DataFlowCarrier) session.getAttribute(DataFlowCarrier.SESSION_ATTRIBUTE_NAME);
		
		IFormSetupFacade formSetupFacade = new FormSetupFacadeImpl();
		// Map<Integer, String> transactionTypes = formSetupFacade.getTransactionTypes();
		// TODO test retrieving form options
		FormOptions formOpts = formSetupFacade.getAddOrEditFormOptionsByUser(dataFlowCarrier.getUser(), Calendar.getInstance());
		
		ITransactionFacade transactionFacade = new TransactionFacadeImpl();
		List<Transaction> transactions = null;
		if( dataFlowCarrier.queryTransactionData.isDefaultInit() ) {
			transactions = transactionFacade.retrieveAllTransactions(dataFlowCarrier.getUser());
		} else {
			transactions = transactionFacade.searchTransactions(dataFlowCarrier.getUser(), 
					dataFlowCarrier.queryTransactionData.getCriteria());
		}		
		
		// TODO Test pageutil
		PageUtil.getInstance().prepareCompleteData(transactions, 10, request);
		PagedData pagedData = PageUtil.getInstance().retvPagedData(1, request);

		Map<String, Object> dataFlowResults = new HashMap<String, Object>();
		// dataFlowResults.put("transactionTypes", transactionTypes);
		// TODO test retrieving form options
		dataFlowResults.put("formOpts", formOpts);
		dataFlowResults.put("pagedData", pagedData);
		dataFlowCarrier.setFlowResults(dataFlowResults);
		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/transaction/QueryTransactionRecord.jsp");
		reqDispatcher.forward(request, response);
		
	}

}
