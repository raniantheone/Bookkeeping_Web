package ranian.bookkeeping.system.experiments;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerySimpleExperimentsServlet
 */
@WebServlet("/VerySimpleExperimentsServlet")
public class VerySimpleExperimentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerySimpleExperimentsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext servletCtx = request.getServletContext();
		Map<String, ServletRegistration> registrationsMap = (Map<String, ServletRegistration>) servletCtx.getServletRegistrations();
		for(Entry<String, ServletRegistration> entry : registrationsMap.entrySet()) {
			
			String key = entry.getKey();
			ServletRegistration registration = entry.getValue();
			String registrationName = registration.getName();
			Collection<String> mappings = registration.getMappings();
			
			StringBuilder sb = new StringBuilder();
			for(String mapping : mappings) {
				sb.append(mapping).append(", ");
			}
			
			String testResult = String.format("registration key:%s ; registration name:%s ; mappings:%s", key, registrationName, sb.toString());
			System.out.println(testResult);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
