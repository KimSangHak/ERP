package com.yuhannci.erp.model.Excel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@JsonIgnoreProperties(value= {"designFile"})
public class PurchaseLine {
	String unitNo;
	String description;
	String modelNo;
	String maker;
	String provider;
	String quantity;
	String spareQuantity;
	String code;
	String comment;
	String remark;
	
	String designFileNo;
	
	// EXTRA ------------------
	// provider 로 찾은 파트너ID
	String partnerId;
	
	final static Pattern twoDigitPattern = Pattern.compile("\\d{2}");		// non-digit
	
	public boolean isEmpty(){
		if(StringUtils.isEmpty(unitNo) || StringUtils.isEmpty(modelNo) || StringUtils.isEmpty(maker) )
			return true;
		
		return false;		
	}
	public boolean isInvalid(){
		if(isEmpty())
			return true;
		
		// 'unitNo' 에는 숫자만 와야 하는데....		
		Matcher match = twoDigitPattern.matcher(unitNo);
		//log.info("[" + unitNo + "] ==> " + match.matches() + ", group = " + (match.matches() ? match.group() : 0));
		if(!match.matches())
			return false;
		
		return false;
	}
}
