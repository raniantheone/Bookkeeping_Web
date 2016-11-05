package ranian.bookkeeping.system.experiments;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
		
		testRetvUrlMappingInfo(request);
		
		String password = "abcd";
		System.out.println(encodePassword(password));;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void testRetvUrlMappingInfo(HttpServletRequest request) {
		
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
			String registrationMappings = sb.toString();
			sb.setLength(0);
			
			Map<String, String> testMap = registration.getInitParameters();
			for(Entry<String, String> paramEntry : testMap.entrySet()) {
				sb.append(paramEntry.getKey()).append(" - ").append(paramEntry.getValue()).append("; ");
			}
			String initParams = sb.toString();
			
			String testResult = String.format(
					"registration key:%s \n registration name:%s \n registration mappings:%s \n initParams:%s", 
					key, 
					registrationName, 
					registrationMappings,
					initParams
					);
			System.out.println(testResult);
		}
		
	}
	
	private String encodePassword(String password) {
		
		MessageDigest md = null;
		String resultString = "";
		try {
			
			md = MessageDigest.getInstance("SHA-1");
			md.update(password.getBytes("UTF-8"));
			byte[] resultBytes = md.digest();
			
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < resultBytes.length; i++) {
				sb.append(Integer.toHexString(resultBytes[i]));
				// sb.append(Integer.toString(0xFF & resultBytes[i]));
			}
			resultString = sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultString;
	}
	
}
