package ranian.bookkeeping.features.transaction.facade;

import java.util.Map;

import ranian.bookkeeping.features.transaction.model.FormOptions;

public interface IFormSetupFacade {

	public Map<Integer, String> getTransactionTypes();
	
	public FormOptions getAddOrEditFormOptionsByUser(Integer userId);
	
}
