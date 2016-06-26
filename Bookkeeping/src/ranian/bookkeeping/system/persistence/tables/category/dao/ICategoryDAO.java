package ranian.bookkeeping.system.persistence.tables.category.dao;

import java.util.List;

import ranian.bookkeeping.system.persistence.tables.category.vo.CategoryVO;

public interface ICategoryDAO {

	public Boolean insertCategoryByUser(CategoryVO category, Integer userId);
	
	public Boolean updateCategoryByUser(CategoryVO category, Integer userId);
	
	public Boolean deleteCategoryByUser(Integer categoryId, Integer userId);
	
	public List<CategoryVO> retvAllCategoriesByUser(Integer userId);
	
}
