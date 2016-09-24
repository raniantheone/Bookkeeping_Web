package ranian.bookkeeping.system.persistence.tables.user.dao.impl;

import com.mysql.jdbc.Statement;

import ranian.bookkeeping.system.persistence.tables.BaseDAO;
import ranian.bookkeeping.system.persistence.tables.user.dao.IUserDAO;
import ranian.bookkeeping.system.persistence.tables.user.vo.UserVO;

public class UserDAO extends BaseDAO implements IUserDAO {
	
	public UserDAO() {
		super();
	}
	
	@Override
	public Integer insertUser(UserVO user) {
		
		String sqlCmd = "insert into USER_AUTHEN (USER_LOGIN_ACC, USER_LOGIN_PW, USER_NAME, USER_MAIL) "
				+ "values (?, ?, ?, ?)";
		
		Integer newlyCreatedUserId = 0;
		try {
			
			conn = connUtil.getMysqlConnection();
			pstmt = conn.prepareStatement(sqlCmd, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getUserLoginAcc());
			pstmt.setString(2, user.getUserLoginPw());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserMail());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			rs.next();
			newlyCreatedUserId = rs.getInt(1);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(rs, pstmt, conn);
		}
		
		return newlyCreatedUserId;
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

	@Override
	public UserVO getAuthenticatedUser(String userAccount, String userPassword) {

		String sqlCmd = "select USER_ID, USER_LOGIN_ACC, USER_LOGIN_PW, USER_NAME, USER_MAIL "
				+ "from USER_AUTHEN "
				+ "where USER_LOGIN_ACC = ? "
				+ "and USER_LOGIN_PW = ?";
		
		UserVO userVo = null;
		try {
			
			conn = connUtil.getMysqlConnection();
			pstmt = conn.prepareStatement(sqlCmd);
			pstmt.setString(1, userAccount);
			pstmt.setString(2, userPassword);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				
				userVo = new UserVO();
				userVo.setUserId(rs.getInt("USER_ID"));
				userVo.setUserLoginAcc(rs.getString("USER_LOGIN_ACC"));
				userVo.setUserLoginPw(rs.getString("USER_LOGIN_PW"));
				userVo.setUserName(rs.getString("USER_NAME"));
				userVo.setUserMail(rs.getString("USER_MAIL"));
				break;
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(rs, pstmt, conn);
		}
		
		return userVo;
	}

	@Override
	public Integer queryExistingAccountAndEmail(String bookkeepingAccount, String userEmail) {

		String sqlCmd = "select count(USER_ID) from USER_AUTHEN "
				+ "where USER_LOGIN_ACC = ? "
				+ "or USER_MAIL = ?";
		
		Integer count = 0;
		try {
			
			conn = connUtil.getMysqlConnection();
			pstmt = conn.prepareStatement(sqlCmd);
			pstmt.setString(1, bookkeepingAccount);
			pstmt.setString(2, userEmail);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				count = rs.getInt(1);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(rs, pstmt, conn);
		}
		
		return count;
	}

}
