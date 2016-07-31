package ranian.bookkeeping.features.category.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ranian.bookkeeping.features.category.facade.ICategoryManagementFacade;
import ranian.bookkeeping.features.category.facade.impl.CategoryManagementFacade;
import ranian.bookkeeping.system.dataflow.DataFlowCarrier;

/**
 * Servlet implementation class DeleteCategoryServlet
 */
@WebServlet("/DeleteCategoryServlet")
public class DeleteCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCategoryServlet() {
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
		
		DataFlowCarrier dataFlowCarrier = DataFlowCarrier.GetCurrentDataFlowCarrier(request);
		
		Boolean operationSuccess = false;
		ICategoryManagementFacade categoryFacade = new CategoryManagementFacade();
		operationSuccess = categoryFacade.deleteCategory(dataFlowCarrier.getUser(), 
				dataFlowCarrier.deleteCategoryData.getCategoryIdForDel());
		
		Map<String, Object> dataFlowResults = new HashMap<String, Object>();
		dataFlowResults.put("operationSuccess", operationSuccess);
		dataFlowResults.put("nextActionUrl", "queryCategories");
		dataFlowResults.put("nextActionDescription", "Back to category management");
		dataFlowCarrier.setFlowResults(dataFlowResults);

		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/OperationResult.jsp");
		reqDispatcher.forward(request, response);
		
	}

}
