package com.yuhannci.erp.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;
import com.yuhannci.erp.mapper.GeneralMapper;
import com.yuhannci.erp.model.DataTableResponse;
import com.yuhannci.erp.model.GeneralSearchForm;
import com.yuhannci.erp.model.GeneralVacationSearch;
import com.yuhannci.erp.model.GeneralWorkSearch;
import com.yuhannci.erp.model.NameDept;
import com.yuhannci.erp.model.PidbackSearchForm;
import com.yuhannci.erp.model.PositionId;
import com.yuhannci.erp.model.db.DeptData;
import com.yuhannci.erp.model.db.GeneralWorkEntry;
import com.yuhannci.erp.model.db.User;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class GeneralService {
	
	@Autowired GeneralMapper generalMapper;
	
	public DataTableResponse getGeneralEntry(GeneralSearchForm form) {
		
		
		System.out.println("서비스 호출!?!?!?!??!?!?!?!?!??!??!");
		
		DataTableResponse res = new DataTableResponse();
		res.setData( generalMapper.selectGeneralUsrEntry(form ));
		res.setTotalRecords( generalMapper.selectGeneralUsrEntryCount(form));
		res.setDraw(form.getDraw());
		return res;		
	}
	
	public List<DeptData> getGeneralDeptData(){
		
		return generalMapper.getGeneralDeptData();
	}
	
	public DataTableResponse getVacationEntry(GeneralVacationSearch form) {
		
		
		System.out.println("서비스 호출!?!?!?!??!?!?!?!?!??!??!");
		
		DataTableResponse res = new DataTableResponse();
		res.setData( generalMapper.selectGeneralVaEntry(form ));
		res.setTotalRecords( generalMapper.selectGeneralVaEntryCount(form));
		res.setDraw(form.getDraw());
		return res;		
	}
	
	public DataTableResponse getGeneralWorkEntry(GeneralWorkSearch form) {
		
		
		System.out.println("서비스 호출!?!?!?!??!?!?!?!?!??!??!");
		
		DataTableResponse res = new DataTableResponse();
		res.setData( generalMapper.selectGeneralWorkEntry(form ));
		res.setTotalRecords( generalMapper.selectGeneralWorkEntryCount(form));
		res.setDraw(form.getDraw());
		
		List<GeneralWorkEntry> logDatas = generalMapper.selectGeneralWorkEntry(form);
		
		for(GeneralWorkEntry logData: logDatas){
			
			System.out.println("******TIME_Start = "+logData.getCommmuteStart());
			System.out.println("******TIME_End = "+logData.getCommmuteEnd());
		}
		
		return res;		
	}
	
	public DataTableResponse getGeneralWorkEntryY(GeneralWorkSearch form) {
		
		
		System.out.println("서비스 호출!?!?!?!??!?!?!?!?!??!??!");
		
		DataTableResponse res = new DataTableResponse();
		res.setData( generalMapper.generalWorkEntryY(form ));
		res.setTotalRecords( generalMapper.selectGeneralWorkEntryCount(form));
		res.setDraw(form.getDraw());
		

		List<GeneralWorkEntry> logDatas = generalMapper.generalWorkEntryY(form);
		
		for(GeneralWorkEntry logData: logDatas){
			
			System.out.println("******YN = "+logData.getYn());
		}
		return res;		
	}
	
	
	

}
