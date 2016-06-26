package ranian.bookkeeping.system.persistence.tables.type.dao;

import java.util.List;

import ranian.bookkeeping.system.persistence.tables.type.vo.TypeVO;

public interface ITypeDAO {

	public List<TypeVO> retvAllTransactionTypes();
	
}
