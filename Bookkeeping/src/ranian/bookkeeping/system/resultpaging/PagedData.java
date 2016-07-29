package ranian.bookkeeping.system.resultpaging;

import java.util.List;

public class PagedData {

	private Integer totalPages;
	
	private Integer currentPage;
	
	private List<?> pagedDataList;
	
	public PagedData(Integer totalPages, Integer currentPage, List<?> pagedDataList) {
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		this.pagedDataList = pagedDataList;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public List<?> getPagedDataList() {
		return pagedDataList;
	}
	
}
