package ranian.bookkeeping.system.persistence.tables.account.dao.impl;

import java.util.ArrayList;
import java.util.List;

import ranian.bookkeeping.system.persistence.tables.BaseDAO;
import ranian.bookkeeping.system.persistence.tables.account.dao.IAccountDAO;
import ranian.bookkeeping.system.persistence.tables.account.vo.AccountVO;

public class AccountDAO extends BaseDAO implements IAccountDAO {
	
	public AccountDAO() {
		super();
	}
	
	@Override
	public Boolean insertAccountByUser(AccountVO account, Integer userId) {
		
		String sqlCmd = "insert into TR_ACCOUNT (USER_ID, "
				+ "ACCOUNT_NAME, "
				+ "ACCOUNT_DESC) "
				+ "values (?, ?, ?)";
		
		Boolean isSuccess = false;
		try {
			
			conn = connUtil.getMysqlConnection();
			pstmt = conn.prepareStatement(sqlCmd);
			pstmt.setInt(1, userId);
			pstmt.setString(2, account.getAccountName());
			pstmt.setString(3, account.getAccountDesc());
			int rowCount = pstmt.executeUpdate();
			isSuccess = rowCount > 0;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(pstmt, conn);
		}
		
		return isSuccess;
	}

	@Override
	public Boolean updateAccountByUser(AccountVO account, Integer userId) {
		
		String sqlCmd = "update TR_ACCOUNT "
				+ "set ACCOUNT_NAME = ?, "
				+ "ACCOUNT_DESC = ? "
				+ "where ACCOUNT_ID = ? "
				+ "and USER_ID = ?";
		
		Boolean isSuccess = false;
		try {
			
			conn = connUtil.getMysqlConnection();
			pstmt = conn.prepareStatement(sqlCmd);
			pstmt.setString(1, account.getAccountName());
			pstmt.setString(2, account.getAccountDesc());
			pstmt.setInt(3, account.getAccountId());
			pstmt.setInt(4, account.getUserId());
			int rowCount = pstmt.executeUpdate();
			isSuccess = rowCount > 0;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(pstmt, conn);
		}
		
		return isSuccess;
	}

	@Override
	public Boolean deleteAccountByUser(Integer accountId, Integer userId) {	
		
		// TODO deal with sql transaction issue
		
		Boolean isSuccess = false;
		
		String sqlCmdStep01 = "delete from TR_RECORD "
				+ "where (TO_ACC_ID = ? or FROM_ACC_ID = ?) "
				+ "and USER_ID = ?";
		
		try {
			
			conn = connUtil.getMysqlConnection();
			pstmt = conn.prepareStatement(sqlCmdStep01);
			pstmt.setInt(1, accountId);
			pstmt.setInt(2, accountId);
			pstmt.setInt(3, userId);
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
			return isSuccess;
		} finally {
			closeResources(pstmt, conn);
		}
		
		String sqlCmdStep02 = "delete from TR_ACCOUNT "
				+ "where ACCOUNT_ID = ? "
				+ "and USER_ID = ?";
		
		try {
			
			conn = connUtil.getMysqlConnection();
			pstmt = conn.prepareStatement(sqlCmdStep02);
			pstmt.setInt(1, accountId);
			pstmt.setInt(2, userId);
			int rowCount = pstmt.executeUpdate();
			isSuccess = rowCount > 0;
			
		} catch(Exception e) {
			e.printStackTrace();
			return isSuccess;
		} finally {
			closeResources(pstmt, conn);
		}
		
		return isSuccess;
	}

	@Override
	public List<AccountVO> retrieveAllAccountsByUser(Integer userId) {

		String sqlCmd = "select ACCOUNT_ID, USER_ID, ACCOUNT_NAME, ACCOUNT_DESC "
				+ "from TR_ACCOUNT "
				+ "where USER_ID = ?";
		
		List<AccountVO> accountVos = new ArrayList<AccountVO>();
		try{
			
			conn = connUtil.getMysqlConnection();
			pstmt = conn.prepareStatement(sqlCmd);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				AccountVO accountVo = new AccountVO();
				accountVo.setAccountId(rs.getInt("ACCOUNT_ID"));
				accountVo.setUserId(rs.getInt("USER_ID"));
				accountVo.setAccountName(rs.getString("ACCOUNT_NAME"));
				accountVo.setAccountDesc(rs.getString("ACCOUNT_DESC"));
				accountVos.add(accountVo);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(rs, pstmt, conn);
		}
		
		return accountVos;
	}

	@Override
	public AccountVO retrieveAccountById(Integer userId, Integer accountId) {
		
		String sqlCmd = "select ACCOUNT_ID, USER_ID, ACCOUNT_NAME, ACCOUNT_DESC "
				+ "from TR_ACCOUNT "
				+ "where USER_ID = ? and "
				+ "ACCOUNT_ID = ?";
		
		AccountVO accountVo = null;
		try{
			
			conn = connUtil.getMysqlConnection();
			pstmt = conn.prepareStatement(sqlCmd);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, accountId);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				
				accountVo = new AccountVO();
				accountVo.setAccountId(rs.getInt("ACCOUNT_ID"));
				accountVo.setUserId(rs.getInt("USER_ID"));
				accountVo.setAccountName(rs.getString("ACCOUNT_NAME"));
				accountVo.setAccountDesc(rs.getString("ACCOUNT_DESC"));
				break;
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(rs, pstmt, conn);
		}
		
		return accountVo;
	}

}
