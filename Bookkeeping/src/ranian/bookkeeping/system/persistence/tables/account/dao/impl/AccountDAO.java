package ranian.bookkeeping.system.persistence.tables.account.dao.impl;

import java.util.ArrayList;
import java.util.List;

import ranian.bookkeeping.system.persistence.tables.BaseDAO;
import ranian.bookkeeping.system.persistence.tables.account.dao.IAccountDAO;
import ranian.bookkeeping.system.persistence.tables.account.vo.AccountVO;

public class AccountDAO extends BaseDAO implements IAccountDAO {

	@Override
	public Boolean insertAccountByUser(AccountVO account, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateAccountByUser(AccountVO account, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteAccountByUser(AccountVO account, Integer userId) {
		// TODO Auto-generated method stub
		return null;
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

}
