package ranian.bookkeeping.system.authentication;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ranian.bookkeeping.system.authentication.facade.IAuthenticationFacade;
import ranian.bookkeeping.system.authentication.facade.mock.AuthenFacadeMock;
import ranian.bookkeeping.system.authentication.model.User;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
//		User user = null;
//		if( session.getAttribute(User.SESSION_ATTR_NAME) == null ) {
//			
//			IAuthenticationFacade authenticationFacade = new AuthenFacadeMock();
//			user = authenticationFacade.validateUser("", ""); // TODO for testing purpose, remove later
//			session.setAttribute(User.SESSION_ATTR_NAME, user);
//			
//		}
		
		// TODO Test hardcode, implement with map later
		Boolean haveToLogin = true;
		String[] whiteList = {"/EntryPage"};
		String requestServletPath = req.getServletPath();
		for(String function : whiteList) {
			if( requestServletPath.equals(function) ) {
				haveToLogin = false;
				break;
			}
		}
		
		if( session.getAttribute(User.SESSION_ATTR_NAME) == null && haveToLogin ) {
			RequestDispatcher reqDispatcher = req.getRequestDispatcher("/WEB-INF/views/SignIn.jsp");
			reqDispatcher.forward(request, response);
			return;
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
