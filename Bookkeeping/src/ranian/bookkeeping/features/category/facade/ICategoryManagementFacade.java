package ranian.bookkeeping.features.category.facade;

import java.util.List;

import ranian.bookkeeping.features.category.model.Category;
import ranian.bookkeeping.system.authentication.model.User;

public interface ICategoryManagementFacade {

	/**
	 * Retrieve all transaction categories for specified user
	 * @param user
	 * @return
	 */
	public List<Category> retvCategories(User user);
	
	/**
	 * Add a transaction category for specified user
	 * @param user
	 * @param category
	 * @return
	 */
	public Boolean addCategory(User user, Category category);
	
	/**
	 * Delete specified transaction category 
	 * @param user
	 * @param categoryId
	 * @return
	 */
	public Boolean deleteCategory(User user, Integer categoryId);
	
	/**
	 * Get specified transaction category for update later
	 * @param user
	 * @param categoryId
	 * @return
	 */
	public Category getCategoryForEdit(User user, Integer categoryId);
	
	/**
	 * Update specified transaction category
	 * @param user
	 * @param category
	 * @return
	 */
	public Boolean updateCategory(User user, Category category);
}
