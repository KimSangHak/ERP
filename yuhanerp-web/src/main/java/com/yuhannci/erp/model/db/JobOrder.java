package com.yuhannci.erp.model.db;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.AbstractMap.SimpleEntry;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import com.yuhannci.erp.model.FieldExtractor;

import lombok.Data;

// job_order 테이블
// 설비진행 및 치공구 진행 관련

@Data
public class JobOrder extends FieldExtractor {

	Long id;

	String orderType; // "JOB" : 설비진행, "JIG" : 치공구 진행
	String orderTypeName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date orderDate;
	
	String customerId;
	String customerName;
	
	String orderNo;
	String orderNoBase;
	String orderNoExtra;
	
	String device;
	
	String businessUserId;
	String businessUserName;
	String designUserId;
	String designUserName;
	String customerUser;
	String designStatus;
	
	String note;
	
	String moveYN;
	
	//컬럼이외값
	int count;
	
	//
	
	int designProgress;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date designDate;		// 출도일
	
	int purchaseProgress;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date purchaseDate;		// 구매일자
	
	int processProgress;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date processDate;		// 가공일
	
	int testProgress;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date testDate;			// 검사일
		
	int assemblyProgress;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date assemblyDate;		// 조립일
	
	int programProgress;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date programDate;			// 배선PGM일 (설비 only)
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date deliveryDate;		// 배송일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date shippingDate;		// 출고일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date installDate;		// 납품일(설치일)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date realInstallDate;	// 실납품일
//	Date appointedDeliveryDate;			// 납품일		==> installDate
//	Date realDeliveryDate;				// 실제 납품일	==> realInstallDate
	
	Integer estimatedDays;
	Integer quantity;					// 수량 (설비 only)
	Integer internalUnitPrice;
	Integer estimatedPrice;
	Integer negotiatedPrice;
	
	String carrierType;	// 배송 방식 (equipment_delivery 테이블)
	
	String 	lastModifiedUserId;
	Date	lastModifiedWhen;
	
	String	deleted;				// 삭제 여부 (Y,N)
	String currentStage;			// 현재 공정 상태
	
	Integer drawingCount;		// 관련 도면 갯수
	
	final static HashMap<String, String> currentStageLabels;
	
	static{
		currentStageLabels = new HashMap<>();
		currentStageLabels.put("A1", "작업지시등록");
		currentStageLabels.put("A2", "도면등록중");
		currentStageLabels.put("C1", "가공요청");
		currentStageLabels.put("C2", "외주견적요청");
		currentStageLabels.put("C2", "외주발주");
		currentStageLabels.put("C3", "가공입고");
		currentStageLabels.put("C4", "가공입고");
		currentStageLabels.put("E1", "구매 등록");
	}
	
	public String getJobStatus(){
		
		if("Y".equals(deleted))
			return "(삭제)";
		
		if(currentStageLabels.containsKey(currentStage))
			return currentStageLabels.get(currentStage);
		
		return "(" + currentStage + ")";
	}

	public boolean isJigJob(){
		if(StringUtils.isEmpty(orderType))
			throw new IllegalArgumentException("작업 유형이 없어 유형을 알 수 없습니다");
		
		return orderType.equalsIgnoreCase("JIG");
	}
	
	public boolean isFinished(){
		if(StringUtils.isEmpty(currentStage))
			throw new IllegalArgumentException("완료 상태가 없어 완료 여부를 확인할 수 없습니다");
		return currentStage.equalsIgnoreCase("F");
	}
	
	public String getJobOrderTypeLabel(){
		if("JOB".equals(orderType))
			return "설비";
		else if("JIG".equals(orderType))
			return "치공구";
		return "(" + orderType + ")";
	}

}
