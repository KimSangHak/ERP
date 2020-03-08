package com.yuhannci.erp.model;

import lombok.Getter;
import lombok.Setter;

public class DataTableResponse {

	// 카운터
	// The draw counter that this object is a response to - from the draw parameter sent as part of the data request. Note that it is strongly recommended for security reasons that you cast this parameter to an integer, rather than simply echoing back to the client what it sent in the draw parameter, in order to prevent Cross Site Scripting (XSS) attacks.
	@Setter @Getter Integer draw;
	
	// 전체 레코드 
	// Total records, before filtering (i.e. the total number of records in the database)
	@Getter Integer recordsTotal;
	
	// 유효한 레코드
	// Total records, after filtering (i.e. the total number of records after filtering has been applied - not just the number of records being returned for this page of data).
	@Getter Integer recordsFiltered;
	
	// 데이터
	@Setter @Getter Object data;
	
	// 에러 정보
	// Optional: If an error occurs during the running of the server-side processing script, you can inform the user of this error by passing back the error message to be displayed using this parameter. Do not include if there is no error.
	@Setter @Getter String error;
	
	public void setTotalRecords(int n) {
		recordsTotal = n;
		recordsFiltered = n;
	}
	public void setRecordsTotal(int n) {
		recordsTotal = n;
		recordsFiltered = n;		
	}
	public void setRecordsFiltered(int n) {
		recordsTotal = n;
		recordsFiltered = n;		
	}
	
	public void setErrorResponse(Exception e) {
		error = e.getMessage();
	}
	public void setErrorResponse() {
		error = "서버 오류로 인해 목록을 찾을 수 없습니다";
	}
}
