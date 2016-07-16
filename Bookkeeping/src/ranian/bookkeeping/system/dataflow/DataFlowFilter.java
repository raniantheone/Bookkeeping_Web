package ranian.bookkeeping.system.dataflow;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class DataFlowFilter
 */
@WebFilter("/DataFlowFilter")
public class DataFlowFilter implements Filter {

    /**
     * Default constructor. 
     */
    public DataFlowFilter() {
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
		
		DataFlowCarrier dataFlowCarrier = null;
		if( session.getAttribute(DataFlowCarrier.SESSION_ATTRIBUTE_NAME) != null ) {
			
			dataFlowCarrier = (DataFlowCarrier) session.getAttribute(DataFlowCarrier.SESSION_ATTRIBUTE_NAME);
			
		} else {
			
			dataFlowCarrier = new DataFlowCarrier();
			session.setAttribute(DataFlowCarrier.SESSION_ATTRIBUTE_NAME, dataFlowCarrier);
			
		}
		dataFlowCarrier.startDataFlow(req);
		
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
