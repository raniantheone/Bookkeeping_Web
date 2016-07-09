package ranian.bookkeeping.features.category.facade.impl;

import java.util.ArrayList;
import java.util.List;

import ranian.bookkeeping.features.category.facade.ICategoryManagementFacade;
import ranian.bookkeeping.features.category.model.Category;
import ranian.bookkeeping.system.authentication.model.User;
import ranian.bookkeeping.system.persistence.tables.category.dao.ICategoryDAO;
import ranian.bookkeeping.system.persistence.tables.category.dao.impl.CategoryDAO;
import ranian.bookkeeping.system.persistence.tables.category.vo.CategoryVO;

public class CategoryManagementFacade implements ICategoryManagementFacade {

	@Override
	public List<Category> retvCategories(User user) {
		
		List<Category> categories = new ArrayList<Category>();
		
		ICategoryDAO categoryDao = new CategoryDAO();
		List<CategoryVO> categoryVos = categoryDao.retvAllCategoriesByUser(user.getUserId());
		for( CategoryVO categoryVo : categoryVos ) {
			categories.add(new Category(categoryVo.getCategoryId(), 
					categoryVo.getUserId(), 
					categoryVo.getCategoryName()));
		}
		
		/*
		 * TODO
		 * check if the category contains any transaction record
		 */
		
		return categories;
	}

	@Override
	public Boolean addCategory(User user, Category category) {

		Boolean isSuccess = false;
		
		ICategoryDAO categoryDao = new CategoryDAO();
		isSuccess = categoryDao.insertCategoryByUser(category.getCategoryName(), user.getUserId());
		
		return isSuccess;
	}

	@Override
	public Boolean deleteCategory(User user, Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateCategory(User user, Category category) {
		
		Boolean isSuccess = false;
		
		ICategoryDAO categoryDao = new CategoryDAO();
		CategoryVO categoryVo = new CategoryVO();
		categoryVo.setCategoryId(category.getCategoryId());
		categoryVo.setUserId(user.getUserId());
		categoryVo.setCategoryName(category.getCategoryName());
		isSuccess = categoryDao.updateCategoryByUser(categoryVo, user.getUserId());
		
		return isSuccess;
	}

	@Override
	public Category getCategoryForEdit(User user, Integer categoryId) {

		Category category = null;
		
		ICategoryDAO categoryDao = new CategoryDAO();
		CategoryVO categoryVo = categoryDao.getCategoryById(categoryId, user.getUserId());
		category = new Category(categoryVo.getCategoryId(), categoryVo.getUserId(), categoryVo.getCategoryName());
		
		return category;
	}

}
