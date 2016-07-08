package ranian.bookkeeping.features.transaction.facade;

import java.util.Calendar;
import java.util.Map;

import ranian.bookkeeping.features.transaction.model.FormOptions;
import ranian.bookkeeping.system.authentication.model.User;

public interface IFormSetupFacade {

	public Map<Integer, String> getTransactionTypes();
	
	/**
	 * Setup front-end add/edit transaction form options
	 * @param user the key to retrieve accounts, categories
	 * @param dateOptsBase the key to get date options in transaction record date dropdown list
	 * @return
	 */
	public FormOptions getAddOrEditFormOptionsByUser(User user, Calendar dateOptsBase);
	
}
