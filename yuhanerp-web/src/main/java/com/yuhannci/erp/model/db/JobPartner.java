package com.yuhannci.erp.model.db;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ineti
 *
 */
@Data
@Slf4j
public class JobPartner implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2105794319411057472L;


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
	String bankName; 
	String bankAccount;  
	String buyType;
	String typeCode; 
	String typeKind; 
	String billingAfter; 
	String corporateNum; 
	String corporateAddr; 
	String corporatePhone; 
	String corporateMail; 
	String billingDay; 
	String personPhone; 
	String billingName; 
	String personName; 
	String personMail; 
	String personPosition;
	String processEnjoy;
	String note;
	
	String partnerTypeName;
	
	final static Pattern standardCorporateNumPattern = Pattern.compile("([0-9]{3})-([0-9]{2})-([0-9]{5})");
		
	public String getCorporateNum1() {
		Matcher match = standardCorporateNumPattern.matcher(corporateNum);
		return match.matches() ? match.group(1) : "";
	}
	public String getCorporateNum2() {
		Matcher match = standardCorporateNumPattern.matcher(corporateNum);
		return match.matches() ? match.group(2) : "";
	}
	public String getCorporateNum3() {
		Matcher match = standardCorporateNumPattern.matcher(corporateNum);
		return match.matches() ? match.group(3) : "";
	}
}
