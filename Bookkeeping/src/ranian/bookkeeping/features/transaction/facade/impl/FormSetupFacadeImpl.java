package ranian.bookkeeping.features.transaction.facade.impl;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ranian.bookkeeping.features.transaction.facade.IFormSetupFacade;
import ranian.bookkeeping.features.transaction.model.FormOptions;
import ranian.bookkeeping.system.authentication.model.User;
import ranian.bookkeeping.system.persistence.tables.account.dao.IAccountDAO;
import ranian.bookkeeping.system.persistence.tables.account.dao.impl.AccountDAO;
import ranian.bookkeeping.system.persistence.tables.account.vo.AccountVO;
import ranian.bookkeeping.system.persistence.tables.category.dao.ICategoryDAO;
import ranian.bookkeeping.system.persistence.tables.category.dao.impl.CategoryDAO;
import ranian.bookkeeping.system.persistence.tables.category.vo.CategoryVO;
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
	public FormOptions getAddOrEditFormOptionsByUser(User user, Calendar dateOptsBase) {
		
		FormOptions formOpts = new FormOptions();
		
		ITypeDAO typeDao = new TypeDAO();
		List<TypeVO> typeVos = typeDao.retvAllTransactionTypes();
		Map<Integer, String> types = new LinkedHashMap<Integer, String>();
		for( TypeVO typeVo : typeVos ) {
			types.put(typeVo.getTypeId(), typeVo.getTypeName());
		}
		formOpts.setTransTypes(types);
		
		Integer userId = user.getUserId();
		
		IAccountDAO accountDao = new AccountDAO();
		List<AccountVO> accountVos = accountDao.retrieveAllAccountsByUser(userId);
		Map<Integer, String> accounts = new LinkedHashMap<Integer, String>();
		for( AccountVO accountVo : accountVos ) {
			accounts.put(accountVo.getAccountId(), accountVo.getAccountName());
		}
		formOpts.setUserAccounts(accounts);
		
		ICategoryDAO categoryDao = new CategoryDAO();
		List<CategoryVO> categoryVos= categoryDao.retvAllCategoriesByUser(userId);
		Map<Integer, String> categories = new LinkedHashMap<Integer, String>();
		for( CategoryVO categoryVo : categoryVos ) {
			categories.put(categoryVo.getCategoryId(), categoryVo.getCategoryName());
		}
		formOpts.setTransCategories(categories);
		
		// create date options ranging from -7d ~ base date ~ +7d
		Map<Long, String> dateRange = new LinkedHashMap<Long, String>();
		long baseMillis = dateOptsBase.getTimeInMillis();
		long dailyIncrementMillis = 24 * 60 * 60 * 1000;
		for(int i = -7; i < 8; i++) {
			
			long dateOptKey = baseMillis + (i * dailyIncrementMillis);
			
			dateOptsBase.setTimeInMillis(dateOptKey);
			int year = dateOptsBase.get(Calendar.YEAR);
			int month = dateOptsBase.get(Calendar.MONTH) + 1;
			int date = dateOptsBase.get(Calendar.DATE);
			String dateOptValue = String.format("%s-%s-%s", year, month, date);
			
			dateRange.put(dateOptKey, dateOptValue);
			
		}
		formOpts.setDateRange(dateRange);
		
		return formOpts;
	}

}
