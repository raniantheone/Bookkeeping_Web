package ranian.bookkeeping.features.category.controller;

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

import ranian.bookkeeping.features.category.facade.ICategoryManagementFacade;
import ranian.bookkeeping.features.category.facade.impl.CategoryManagementFacade;
import ranian.bookkeeping.features.category.model.Category;
import ranian.bookkeeping.system.authentication.model.User;
import ranian.bookkeeping.system.dataflow.DataFlowCarrier;

/**
 * Servlet implementation class queryCategoriesServlet
 */
@WebServlet("/queryCategoriesServlet")
public class queryCategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public queryCategoriesServlet() {
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
		DataFlowCarrier dataFlowCarrier = (DataFlowCarrier) session.getAttribute(DataFlowCarrier.SESSION_ATTR_NAME);
		
		User user = dataFlowCarrier.getUser();
		ICategoryManagementFacade categoryFacade = new CategoryManagementFacade();
		List<Category> categories = categoryFacade.retvCategories(user);
		
		Map<String, Object> dataFlowResults = new HashMap<String, Object>();
		dataFlowResults.put("categories", categories);
		dataFlowCarrier.setFlowResults(dataFlowResults);

		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/CategoryManagement.jsp");
		reqDispatcher.forward(request, response);
		
	}

}
