package com.yuhannci.erp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class JobOrderModificationEntry {
	Long id;
	
	String orderType;
	Long jobOrderId;
	String category;
	
	String changeType;			// MODIFY_STATUS = 상태변경, MODIFY_FIELD = 값 변경, DELETE = 컨셉삭제

	String userName;			// join 으로 가져올 수정한 사람의 이름
	
	Date whenModified;
	
	String fieldName;
	String before;
	String after;
	
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public String getText(){
		
		StringBuilder sb = new StringBuilder();
		sb.append(userName);
		sb.append(" 님이 ");
		sb.append(sdf.format(whenModified));
		sb.append(" 에 ");
		if("CHANGE".equals(changeType)){
			sb.append(fieldName);
			sb.append(" 을 ");
			sb.append(before);
			sb.append(" 에서 ");
			sb.append(after);
			sb.append(" 로 변경했습니다");
		}else if("DELETE".equals(changeType)){
			sb.append("삭제 했습니다");
		}
		
		return sb.toString();
		
	}
}
