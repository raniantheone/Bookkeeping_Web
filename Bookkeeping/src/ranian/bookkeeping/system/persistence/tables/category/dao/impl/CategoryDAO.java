package ranian.bookkeeping.system.persistence.tables.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import ranian.bookkeeping.system.persistence.tables.BaseDAO;
import ranian.bookkeeping.system.persistence.tables.category.dao.ICategoryDAO;
import ranian.bookkeeping.system.persistence.tables.category.vo.CategoryVO;

public class CategoryDAO extends BaseDAO implements ICategoryDAO {

	@Override
	public Boolean insertCategoryByUser(CategoryVO category, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateCategoryByUser(CategoryVO category, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteCategoryByUser(Integer categoryId, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryVO> retvAllCategoriesByUser(Integer userId) {

		String sqlCmd = "select CATEGORY_ID, USER_ID, CATEGORY_NAME, CATEGORY_DEL "
				+ "from TR_CATEGORY "
				+ "where USER_ID = ?";
		
		List<CategoryVO> categoryVos = new ArrayList<CategoryVO>();
		try{
			
			conn = connUtil.getMysqlConnection();
			pstmt = conn.prepareStatement(sqlCmd);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				
				CategoryVO categoryVo = new CategoryVO();
				categoryVo.setCategoryId(rs.getInt("CATEGORY_ID"));
				categoryVo.setUserId(rs.getInt("USER_ID"));
				categoryVo.setCategoryName(rs.getString("CATEGORY_NAME"));
				categoryVo.setCategoryDel(rs.getBoolean("CATEGORY_DEL"));
				categoryVos.add(categoryVo);
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(rs, pstmt, conn);
		}
		
		return categoryVos;
	}

}
