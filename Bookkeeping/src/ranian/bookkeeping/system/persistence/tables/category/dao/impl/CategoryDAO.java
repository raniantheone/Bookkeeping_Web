package ranian.bookkeeping.system.persistence.tables.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import ranian.bookkeeping.system.persistence.tables.BaseDAO;
import ranian.bookkeeping.system.persistence.tables.category.dao.ICategoryDAO;
import ranian.bookkeeping.system.persistence.tables.category.vo.CategoryVO;

public class CategoryDAO extends BaseDAO implements ICategoryDAO {
	
	public CategoryDAO() {
		super();
	}
	
	@Override
	public Boolean insertCategoryByUser(String categoryName, Integer userId) {

		String sqlCmd = "insert into TR_CATEGORY ("
				+ "USER_ID, CATEGORY_NAME) "
				+ "values (?, ?)";
		
		Boolean isSuccess = false;
		try{
			
			conn = connUtil.getMysqlConnection();
			pstmt = conn.prepareStatement(sqlCmd);
			pstmt.setInt(1, userId);
			pstmt.setString(2, categoryName);
			int rowCount = pstmt.executeUpdate();
			isSuccess = rowCount > 0 ? true : false;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(pstmt, conn);
		}
		
		return isSuccess;
	}

	@Override
	public Boolean updateCategoryByUser(CategoryVO categoryVo, Integer userId) {
		
		String sqlCmd = "update TR_CATEGORY "
				+ "set CATEGORY_NAME = ? "
				+ "where USER_ID = ? "
				+ "and CATEGORY_ID = ?";
		
		Boolean isSuccess = false;
		try{
			
			conn = connUtil.getMysqlConnection();
			pstmt = conn.prepareStatement(sqlCmd);
			pstmt.setString(1, categoryVo.getCategoryName());
			pstmt.setInt(2, userId);
			pstmt.setInt(3, categoryVo.getCategoryId());
			int rowCount = pstmt.executeUpdate();
			isSuccess = rowCount > 0 ? true : false;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(pstmt, conn);
		}
		
		return isSuccess;
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

	@Override
	public CategoryVO getCategoryById(Integer categoryId, Integer userId) {

		String sqlCmd = "select CATEGORY_ID, USER_ID, CATEGORY_NAME, CATEGORY_DEL "
				+ "from TR_CATEGORY "
				+ "where CATEGORY_ID = ? "
				+ "and USER_ID = ?";
		
		CategoryVO categoryVo = null;
		try{
			
			conn = connUtil.getMysqlConnection();
			pstmt = conn.prepareStatement(sqlCmd);
			pstmt.setInt(1, categoryId);
			pstmt.setInt(2, userId);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				
				categoryVo = new CategoryVO();
				categoryVo.setCategoryId(rs.getInt("CATEGORY_ID"));
				categoryVo.setUserId(rs.getInt("USER_ID"));
				categoryVo.setCategoryName(rs.getString("CATEGORY_NAME"));
				break;
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(rs, pstmt, conn);
		}
		
		return categoryVo;
	}

}
