package ranian.bookkeeping.features.transaction.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
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
import ranian.bookkeeping.system.authentication.model.User;
import ranian.bookkeeping.system.dataflow.DataFlowCarrier;

/**
 * Servlet implementation class SetupFormServlet
 */
@WebServlet("/SetupFormServlet")
public class SetupFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetupFormServlet() {
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
		
		User user = dataFlowCarrier.getUser();
		Integer typeId = null;
		
		IFormSetupFacade formSetupFacade = new FormSetupFacadeImpl();
		Calendar cal = Calendar.getInstance();
		Transaction transToEdit = null;
		FormOptions formOpts = null;
		if( dataFlowCarrier.formSetupData.isEdit() ) {
			// Prepare edit transaction form
			ITransactionFacade transFacade = new TransactionFacadeImpl();
			transToEdit = transFacade.getTransactionForEdit(user, dataFlowCarrier.formSetupData.getTransIdForEdit());
			typeId = transToEdit.getTransType();
			
			cal.setTimeInMillis(transToEdit.getTransRecordTime().getTime());
			
		} else {
			
			typeId = dataFlowCarrier.formSetupData.getTransType();
			
		}
		formOpts = formSetupFacade.getAddOrEditFormOptionsByUser(user, cal);

		Map<String, Object> dataFlowResults = new HashMap<String, Object>();
		dataFlowResults.put("formOpts", formOpts);
		dataFlowResults.put("typeId", typeId);
		dataFlowResults.put("transToEdit", transToEdit); // null if dealing with an add transaction form
		dataFlowCarrier.setFlowResults(dataFlowResults);

		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/AddOrEditTransactionRecord.jsp");
		reqDispatcher.forward(request, response);
		
	}

}
