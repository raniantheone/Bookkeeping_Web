package ranian.bookkeeping.system.authentication.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ranian.bookkeeping.system.authentication.facade.IAuthenticationFacade;
import ranian.bookkeeping.system.authentication.facade.impl.AuthenticationFacade;
import ranian.bookkeeping.system.authentication.model.User;
import ranian.bookkeeping.system.dataflow.DataFlowCarrier;

/**
 * Servlet implementation class SignInController
 */
@WebServlet("/SignInController")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
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
		
		DataFlowCarrier dataFlowCarrier = DataFlowCarrier.GetCurrentDataFlowCarrier(request);
		
		User user = null;
		if( dataFlowCarrier.signInPageData.isLoggedInUser() ) {
			
			user = dataFlowCarrier.getUser();
			
		} else {
			
			if( !dataFlowCarrier.signInPageData.isPageInit() ) {
			
				IAuthenticationFacade authenFacade = new AuthenticationFacade();
				user = authenFacade.validateUser(dataFlowCarrier.signInPageData.getUserAccount(), 
						dataFlowCarrier.signInPageData.getUserPassword());
				request.getSession().setAttribute(User.SESSION_ATTR_NAME, user);
				
				String originalUrl = null;
				if( request.getSession().getAttribute("ORIGINAL_URL") != null ) {
					originalUrl = (String)request.getSession().getAttribute("ORIGINAL_URL");	
				}

				if( originalUrl != null && user != null ) {
					request.getSession().removeAttribute("ORIGINAL_URL");
					response.sendRedirect(originalUrl);
					return;
				}
				
			}

		}
		
		Map<String, Object> dataFlowResults = new HashMap<String, Object>();
		dataFlowResults.put("user", user);
		dataFlowCarrier.setFlowResults(dataFlowResults);

		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/SignIn.jsp");
		reqDispatcher.forward(request, response);
		
	}

}
