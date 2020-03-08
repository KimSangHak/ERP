package com.yuhannci.erp.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

// 설비 컨셉 작업 지시 등록
// 에서 입력해서 전송한 양식 맵핑

@Data
public class JobConceptRegForm {
	
	public enum RegMode{
		create,
		update
	}
	
	@NotNull
	String orderType;
	
	@NotNull
	RegMode mode;	// create or update
	
	Long id;		// null if create
	
	@NotNull
	String customerId;
	
	String orderNoBase;	// 업체 코드와 동일
	String orderNoExtra;
	
	@NotNull 
	String device;
	@NotNull
	@Range(min=1)
	Integer quantity;
	@NotNull
	String businessUserId;
	@NotNull
	String designUserId;
	@NotNull
	String customerUser;
	
	String note;
	
	Integer internalUnitPrice;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date internalUnitPriceSharedDate;
	
	Integer estimatedPrice;
	Integer NegotiatedPrice;	
	
}
