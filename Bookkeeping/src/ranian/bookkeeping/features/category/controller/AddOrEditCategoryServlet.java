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
import javax.servlet.http.HttpSession;

import ranian.bookkeeping.features.category.facade.ICategoryManagementFacade;
import ranian.bookkeeping.features.category.facade.impl.CategoryManagementFacade;
import ranian.bookkeeping.features.category.model.Category;
import ranian.bookkeeping.system.authentication.model.User;
import ranian.bookkeeping.system.dataflow.DataFlowCarrier;

/**
 * Servlet implementation class AddOrEditCategoryServlet
 */
@WebServlet("/AddOrEditCategoryServlet")
public class AddOrEditCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrEditCategoryServlet() {
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
		DataFlowCarrier dataFlowCarrier = (DataFlowCarrier) session.getAttribute(DataFlowCarrier.SESSION_ATTRIBUTE_NAME);
		
		ICategoryManagementFacade categoryFacade = new CategoryManagementFacade();
		User user = dataFlowCarrier.getUser();
		Boolean operationSuccess = false;
		if( dataFlowCarrier.addOrEditCategoryData.isEdit() ) {
			
			Category categoryForEdit = dataFlowCarrier.addOrEditCategoryData.getCategoryForEdit();
			operationSuccess = categoryFacade.updateCategory(user, categoryForEdit);
			
		} else {
			
			Category categoryForAdd = dataFlowCarrier.addOrEditCategoryData.getCategoryForAdd();
			operationSuccess = categoryFacade.addCategory(user, categoryForAdd);
			
		}
		
		Map<String, Object> dataFlowResults = new HashMap<String, Object>();
		dataFlowResults.put("operationSuccess", operationSuccess);
		dataFlowResults.put("nextActionUrl", "queryCategories");
		dataFlowResults.put("nextActionDescription", "Back to category management");
		dataFlowCarrier.setFlowResults(dataFlowResults);

		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/OperationResult.jsp");
		reqDispatcher.forward(request, response);
		
	}

}
