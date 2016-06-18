package tw.ranian.bookkeeping.transaction.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.ranian.bookkeeping.authentication.facade.mock.AuthenFacadeMock;
import tw.ranian.bookkeeping.authentication.model.User;
import tw.ranian.bookkeeping.transaction.facade.ITransactionFacade;
import tw.ranian.bookkeeping.transaction.facade.impl.TransactionFacadeImpl;
import tw.ranian.bookkeeping.transaction.model.Transaction;

/**
 * Servlet implementation class NewTransactionServlet
 */
@WebServlet("/NewTransactionServlet")
public class NewTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewTransactionServlet() {
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
		
		AuthenFacadeMock authenFacadeMock = new AuthenFacadeMock();
		ITransactionFacade transactionFacade = new TransactionFacadeImpl();
		
		Transaction transaction = new Transaction();
		transaction.setTransAmount(100.0f);
		transaction.setToAccId(1);
		transaction.setTransType(1);
		transaction.setTransCategory(1);
		transaction.setTransRecordTime(new Timestamp(new Date().getTime()));
		
		User user = authenFacadeMock.createNewTestUser();
		Boolean isSuccess = transactionFacade.createTransaction(user, transaction);
		
		System.out.println("post hit");
		response.getWriter().append("Served at: ").append(request.getContextPath())
							.append("\nTransaction created: ").append(isSuccess.toString());

	}

}
