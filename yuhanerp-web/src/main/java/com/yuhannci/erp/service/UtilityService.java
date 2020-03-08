package com.yuhannci.erp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yuhannci.erp.mapper.CommonMapper;

@Service
public class UtilityService {
	
	@Autowired CommonMapper commonMapper;

	// 콤마로 구분된 숫자를 분리해서 전달
	public List<Long> tokenizeValues(String src){
		String[] tokens = src.split(",");
		List<Long> values = new ArrayList(tokens.length);
		for(int i=0;i<tokens.length;i++){
			if(StringUtils.isEmpty(tokens[i]))
				continue;
			values.add( Long.parseLong(tokens[i]) );
		}
		return values;
	}
	
	public String generateId(String sectionId, String partnerId){
		
		if(partnerId != null && partnerId.length() != 5)
			throw new IllegalArgumentException("파트너ID 는 5자리만 허용됩니다");
		
		return commonMapper.selectTransactionId(sectionId, partnerId);		
	}
}
