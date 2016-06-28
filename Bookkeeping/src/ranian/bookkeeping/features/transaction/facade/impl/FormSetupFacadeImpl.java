package ranian.bookkeeping.features.transaction.facade.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ranian.bookkeeping.features.transaction.facade.IFormSetupFacade;
import ranian.bookkeeping.features.transaction.model.FormOptions;
import ranian.bookkeeping.system.persistence.tables.type.dao.ITypeDAO;
import ranian.bookkeeping.system.persistence.tables.type.dao.impl.TypeDAO;
import ranian.bookkeeping.system.persistence.tables.type.vo.TypeVO;

public class FormSetupFacadeImpl implements IFormSetupFacade {

	@Override
	public Map<Integer, String> getTransactionTypes() {
		
		ITypeDAO typeDao = new TypeDAO();
		List<TypeVO> typeVoList = typeDao.retvAllTransactionTypes();
		Map<Integer, String> transactionTypes = new LinkedHashMap<Integer, String>();
		for(TypeVO typeVo : typeVoList) {
			transactionTypes.put(typeVo.getTypeId(), typeVo.getTypeName());
		}
		
		return transactionTypes;
	}
	@Override
	public FormOptions getAddOrEditFormOptionsByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
