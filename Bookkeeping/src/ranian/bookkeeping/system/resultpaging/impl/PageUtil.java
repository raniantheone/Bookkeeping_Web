package ranian.bookkeeping.system.resultpaging.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ranian.bookkeeping.system.resultpaging.IPageUtil;
import ranian.bookkeeping.system.resultpaging.PagedData;

/**
 * It is meant to be used in servlets.
 * @author Ranian
 *
 */
public class PageUtil implements IPageUtil {
	
	public static final PageUtil PAGE_UTILITY;
	
	static {
		PAGE_UTILITY = new PageUtil();
	}
	
	private PageUtil() {
		
	}
	
	public static PageUtil getInstance() {
		return PAGE_UTILITY;
	} 
	
	@Override
	public void prepareCompleteData(List<?> rawData, Integer entryPerPage, HttpServletRequest request) {
		
		String retrievalKey = String.format("AllPagedData_%s", request.getServletPath());
		List<List<Object>> listOfPagedData = new ArrayList<List<Object>>();
		
		List<Object> pagedData = null;
		for(int i = 0; i < rawData.size(); i++) {
			
			if( i % entryPerPage == 0 ) {
				pagedData = new ArrayList<Object>();
				listOfPagedData.add(pagedData);
			}
			pagedData.add(rawData.get(i));
			
		}
		
		request.getSession().setAttribute(retrievalKey, listOfPagedData);
	}

	@Override
	public PagedData retvPagedData(Integer pageNum, HttpServletRequest request) {
		
		String retrievalKey = String.format("AllPagedData_%s", request.getServletPath());
		
		@SuppressWarnings("unchecked")
		List<List<Object>> listOfPagedData = (List<List<Object>>) request.getSession().getAttribute(retrievalKey);
		
		// TODO deal with index out of bound
		PagedData pagedData = new PagedData(listOfPagedData.size(), pageNum, listOfPagedData.get(pageNum - 1));
		
		return pagedData;
	}
	
	
}
