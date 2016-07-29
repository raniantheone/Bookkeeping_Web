package ranian.bookkeeping.system.resultpaging;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface IPageUtil {
	
	/**
	 * Set complete data for retrieving paged result later
	 * @param rawData
	 * @param entryPerPage
	 * @param request
	 */
	public void prepareCompleteData(List<?> rawData, Integer entryPerPage, HttpServletRequest request);
	

	public PagedData retvPagedData(Integer pageNum, HttpServletRequest request);
	
}
