package com.yuhannci.erp.model;

import lombok.Data;

@Data
public class PagingInfo {
	int currentPageNo;
	int totalPageCount;
	int pageSize;
	int pageOffset;
	
	public int getInitialIndexPageNo(){		
		return (currentPageNo - 1) / 10 * 10 + 1;		
	}
	
	public int getLastIndexPageNo(){
		return Math.min(getInitialIndexPageNo() + 9, totalPageCount);
	}
	
	public int getPreviousPageNo(){
		return currentPageNo - 1 < 1 ? 1 : currentPageNo - 1;
	}
	
	public int getNextPageNo(){
		return currentPageNo + 1 > totalPageCount ? totalPageCount : currentPageNo + 1;
	}
	
	public int getPrevious10PageNo(){
		return currentPageNo - 10 < 1 ? 1 : currentPageNo - 10;
	}
	
	public int getNext10PageNo(){
		return currentPageNo + 10 > totalPageCount ? totalPageCount : currentPageNo + 10;
	}
}
