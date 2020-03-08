package com.yuhannci.erp.model.db;

import lombok.Data;

/**
 * @author ineti
 *
 */
@Data
public class BuyPartner {
	/**
	 *업체코드 
	 */
	String id;
	
	
	/**
	 * 업체명
	 */
	String partnerName;
	
	/**
	 * 업체종류
	 * M : 가공 
	 */
	String buyType;
}
