package com.yuhannci.erp.model.db;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.AbstractMap.SimpleEntry;
import java.util.Date;
import java.util.HashMap;

import org.apache.ibatis.javassist.compiler.ast.Pair;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

// 설비 컨셉 및 치공구 컨셉
// concept_job_order 테이블

@Data
@Slf4j
public class ConceptJobOrder {

	Long id;
	
	String orderType;					// "CONCEPT" : 설비 컨셉, "JIG_CONCEPT" : 치공구컨셉
	Date orderDate;						// 등록일
	
	String customerId;					// 파트너ID
	String customerName;					// 파트너이름(FK)

	String orderNo;			// 표시용 order-No
	String orderNoBase;		
	String orderNoExtra;		

	String deleted;						// 삭제 여부 Y/N

	String device;

	// 컨셉 작업 완료 날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date conceptFinishDate;			
	
	String businessUserId;
	String businessUserName;
	String designUserId;
	String designUserName;
	String customerUser;
	
	// 비고
	String note;

	Integer quantity;
	
	Integer internalUnitPrice;
	Integer estimatedPrice;
	Integer negotiatedPrice;
	
	// 내부단가 공유일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date internalUnitPriceSharedDate;
	
	Long internalPriceFileNo;
	Long conceptFileNo;	
		
	String currentStage;
	
	String conceptFileHash, internalPriceFileHash;
	
	String lastModifiedUserId;
	Date lastModifiedWhen;
	
	public String getCurrentStageDescription() throws Exception{
		if(currentStage == null)
			return "-";
		
		switch(currentStage.charAt(0)){
		// 컨셉 완료 전
		case 'R':
			return "컨셉 진행";
			
		// 컨셉 완료 후			
		case 'A':
			return "단가 계산";
		case 'B':
			return "승인 대기";
		case 'C':
			return "견적서 작성";
		case 'D':
			return "견적서 송부";
		case 'F':
			return "작업지시";
		default:
			throw new Exception("current_stage 값이 잘못되었습니다. id=" + id + ", currentStage=" + currentStage);
		}		
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	// 현재 instance 의 값을 리스트화 
	protected HashMap<String, String> extractFields(){
		HashMap<String, String> result =  new HashMap<String, String>();
		Field[] fields = this.getClass().getDeclaredFields();
		Object value = null;
		for(Field field : fields){
			field.setAccessible(true);
			
			//Class type = field.getType();
			try{
				value = field.get( this);
			}catch(Exception e){
				value = null;
			}
			
			if(value == null){
				result.put(field.getName(), "");
				continue;
			}
			
			result.put(field.getName(), String.valueOf(value) );			

		}
		return result;
	}
	
	// 원래 값에서 변경된 것을 찾아서 반환
	public HashMap<String, SimpleEntry<String, String>> compare(ConceptJobOrder original){
		
		HashMap<String, String> org = original.extractFields();
		HashMap<String, String> mine = this.extractFields();
		
		HashMap<String, SimpleEntry<String, String>> altered = new HashMap<>();
		
		for(String key : org.keySet()){
			
			String org_value = org.get(key);
			String new_value = mine.get(key);
			if(!org_value.equals(new_value))
				altered.put(key, new SimpleEntry<String, String>(org_value, new_value));			
		}
		
		return altered;
	}
}
