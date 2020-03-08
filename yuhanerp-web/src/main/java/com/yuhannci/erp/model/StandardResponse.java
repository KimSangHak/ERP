package com.yuhannci.erp.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class StandardResponse {
	String result;
	String reason;
	String value;
	Object data;
	
	@JsonFormat(pattern="yyyyMMddHHmmss")
	Date timestamp;
	
	public static StandardResponse createResponse(String result, String reason){
		StandardResponse res = new StandardResponse();
		res.result = result;
		res.reason = reason;
		res.timestamp = new Date();
		return res;
	}
	
	public static StandardResponse createResponse(String result){
		return createResponse(result, null);
	}
	
	public static StandardResponse createSuccessResponse(){
		return createResponse("OK", null);
	}
	
	public static StandardResponse createErrorResponse(Exception e){
		return createResponse("ERROR", e.getMessage());
	}
	public static StandardResponse createErrorResponse(){
		return createResponse("ERROR", "서버 오류로 처리 할 수 없습니다");
	}
}
