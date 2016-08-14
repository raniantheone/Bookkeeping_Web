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
import ranian.bookkeeping.system.authentication.model.User;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {
	
	private FilterConfig authenFilterConf;
	
    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {

    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();		
		
		/**
		 * Functions which do not require authentication are defined in init-params of authentication filter
		 * Refer to web.xml for exact details 
		 */
		Boolean haveToLogin = authenFilterConf.getInitParameter(req.getServletPath()) == null;
		if( haveToLogin && session.getAttribute(User.SESSION_ATTR_NAME) == null ) {
			session.setAttribute("ORIGINAL_URL", req.getRequestURL().toString());
			RequestDispatcher reqDispatcher = req.getRequestDispatcher("/WEB-INF/views/SignIn.jsp");
			reqDispatcher.forward(request, response);
			return;
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
		this.authenFilterConf = fConfig;
		
	}

}
