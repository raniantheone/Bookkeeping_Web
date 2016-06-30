package ranian.bookkeeping.system.persistence.tables.user.dao.impl;

import ranian.bookkeeping.system.persistence.tables.BaseDAO;
import ranian.bookkeeping.system.persistence.tables.user.dao.IUserDAO;
import ranian.bookkeeping.system.persistence.tables.user.vo.UserVO;

public class UserDAO extends BaseDAO implements IUserDAO {
	
	public UserDAO() {
		super();
	}
	
	@Override
	public Boolean insertUser(UserVO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateUser(UserVO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteUser(UserVO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO getUserById(Integer userId) {
		
		String sqlCmd = "select USER_ID, USER_LOGIN_ACC, USER_LOGIN_PW, "
				+ "USER_NAME, USER_MAIL "
				+ "from USER_AUTHEN "
				+ "where USER_ID = ?";
		UserVO userVo = new UserVO();
		
		try{
			
			conn = connUtil.getMysqlConnection();
			pstmt = conn.prepareStatement(sqlCmd);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				
				
				userVo.setUserId(rs.getInt("USER_ID"));
				userVo.setUserLoginAcc(rs.getString("USER_LOGIN_ACC"));
				userVo.setUserLoginPw(rs.getString("USER_LOGIN_PW"));
				userVo.setUserName(rs.getString("USER_NAME"));
				userVo.setUserMail(rs.getString("USER_MAIL"));
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(rs, pstmt, conn);
		}
		
		return userVo;
	}

	@Override
	public UserVO getUserByLoginAccount(String userLoginAccount) {
		// TODO Auto-generated method stub
		return null;
	}

}
