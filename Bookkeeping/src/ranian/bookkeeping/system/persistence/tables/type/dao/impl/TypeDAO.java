package ranian.bookkeeping.system.persistence.tables.type.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ranian.bookkeeping.system.persistence.tables.BaseDAO;
import ranian.bookkeeping.system.persistence.tables.type.dao.ITypeDAO;
import ranian.bookkeeping.system.persistence.tables.type.vo.TypeVO;

public class TypeDAO extends BaseDAO implements ITypeDAO {
	
	public TypeDAO() {
		super();
	}
	
	@Override
	public List<TypeVO> retvAllTransactionTypes() {
		
		List<TypeVO> typeVoList = new ArrayList<TypeVO>();
		
		String sqlCmd = "select TYPE_ID, TYPE_NAME, TYPE_DEL from TR_TYPE";
		
		try{
			
			conn = connUtil.getMysqlConnection();
			pstmt = conn.prepareStatement(sqlCmd);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				TypeVO typeVo = new TypeVO();
				typeVo.setTypeId(rs.getInt("TYPE_ID"));
				typeVo.setTypeName(rs.getString("TYPE_NAME"));
				typeVo.setTypeDel(rs.getBoolean("TYPE_DEL"));
				typeVoList.add(typeVo);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(rs, pstmt, conn);
		}
		
		return typeVoList;
	}

}
