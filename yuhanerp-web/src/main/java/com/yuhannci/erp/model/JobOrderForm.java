package com.yuhannci.erp.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

// 설비 작업 지시 등록
// 에서 입력해서 전송한 양식 맵핑

@Data
public class JobOrderForm {
	@NotNull	String partnerId;
	
	@NotNull	String orderNoBase;	// 업체 코드와 동일
	@NotNull	String orderNoExtra;
	
	@NotNull	String device;
	
	String installDate;			// 납품일
	String realInstallDate;		// 실 납품일
	String shippingDate;		// 출고일
	
	@NotNull	String businessUserId;
	@NotNull	String designUserId;
	@NotNull	String customerUser;
	
	String note;	// 비고
	
	Date designDate;
	Date processDate;
	Date testDate;
	Date assemblyDate;		
	Integer quantity;			// 수량
	Integer estimatedDays;		// 소요기간
	Integer internalUnitPrice;	// 내부단가
	
	Integer estimatedPrice;		// 견적금액
	Integer NegotiatedPrice;	// 내고금액
	
	String jobFrom;
}
