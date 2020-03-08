package com.yuhannci.erp.model.db;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.util.StringUtils;

import lombok.Data;

@Data
public class JobDelivery {

	Long id;
	
	Long	jobOrderId;
	
	String orderNoBase;
	String orderNoExtra;
	
	Date orderDate;
	
	String carrierName;
	String carrierType;
	String destination;
	Integer shippingFee;
	Long imageFileNo1;
	Long imageFileNo2;
	Long imageFileNo3;
	Long imageFileNo4;
	Long imageFileNo5;	
	
	String imageFileHashNo1;
	String imageFileHashNo2;
	String imageFileHashNo3;
	String imageFileHashNo4;
	String imageFileHashNo5;
	
	public List<String> getImageFileHashes(){
		List<String> list = new LinkedList<>();
		if(!StringUtils.isEmpty(imageFileHashNo1)) list.add(imageFileHashNo1);
		if(!StringUtils.isEmpty(imageFileHashNo2)) list.add(imageFileHashNo2);
		if(!StringUtils.isEmpty(imageFileHashNo3)) list.add(imageFileHashNo3);
		if(!StringUtils.isEmpty(imageFileHashNo4)) list.add(imageFileHashNo4);
		if(!StringUtils.isEmpty(imageFileHashNo5)) list.add(imageFileHashNo5);
		
		return list;
	}
}
