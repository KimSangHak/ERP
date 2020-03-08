package com.yuhannci.erp.mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.GeneralSearchForm;
import com.yuhannci.erp.model.GeneralVacationSearch;
import com.yuhannci.erp.model.GeneralWorkSearch;
import com.yuhannci.erp.model.db.DeptData;
import com.yuhannci.erp.model.db.GeneralEntry;
import com.yuhannci.erp.model.db.GeneralVacationEntry;
import com.yuhannci.erp.model.db.GeneralWorkEntry;




@Mapper
public interface GeneralMapper {
	
	public Integer selectGeneralUsrEntryCount(GeneralSearchForm form);
	
	public List<GeneralEntry> selectGeneralUsrEntry(GeneralSearchForm form);
	
	public Integer selectGeneralVaEntryCount(GeneralVacationSearch form);
	
	public List<GeneralVacationEntry> selectGeneralVaEntry(GeneralVacationSearch form);
	
	public Integer selectGeneralWorkEntryCount(GeneralWorkSearch form);
	
	public List<GeneralWorkEntry> selectGeneralWorkEntry(GeneralWorkSearch form);
	
	public List<GeneralWorkEntry> generalWorkEntryY(GeneralWorkSearch form);
	
	public List<DeptData> getGeneralDeptData();
	

}
