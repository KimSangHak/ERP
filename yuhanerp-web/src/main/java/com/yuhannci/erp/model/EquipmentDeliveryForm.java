package com.yuhannci.erp.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class EquipmentDeliveryForm {
	
	@NotNull
	Long jobOrderId;
	
	String carrierName;
	String carrierType;
	String destination;
	Integer shippingFee;
	
	// 아래 파일은 폼 전송 후 파일 저장 후 별도로 기록된다
	Long imageFileNo1;
	Long imageFileNo2;
	Long imageFileNo3;
	Long imageFileNo4;
	Long imageFileNo5;
	
}
