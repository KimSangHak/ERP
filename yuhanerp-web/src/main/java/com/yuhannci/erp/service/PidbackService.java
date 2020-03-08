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

import com.google.common.base.Strings;
import com.yuhannci.erp.mapper.PidbackMapper;
import com.yuhannci.erp.model.DataTableResponse;
import com.yuhannci.erp.model.PibackPurchase;
import com.yuhannci.erp.model.PidbackDrawing;
import com.yuhannci.erp.model.PidbackInsForm;
import com.yuhannci.erp.model.PidbackSearchForm;
import com.yuhannci.erp.model.db.PidbackComment;
import com.yuhannci.erp.model.db.PidbackEntry;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PidbackService {
	
	@Autowired PidbackMapper pidbackMapper;
	
	public DataTableResponse getPidbackEntry(PidbackSearchForm form) {
		
		
		System.out.println("서비스 호출!?!?!?!??!?!?!?!?!??!??!");
		
		DataTableResponse res = new DataTableResponse();
		res.setData( pidbackMapper.selectPidbackEntry(form ));
		res.setTotalRecords( pidbackMapper.selectPidbackEntryCount(form));
		res.setDraw(form.getDraw());
		return res;		
	}
	
	public List<PibackPurchase> getPidbackPurchase(String modelNo, String description){
		
		return pidbackMapper.getPidbackPurchase(modelNo, description);
	}
	
	public List<PidbackDrawing> getPidbackDrawing(String orderNoBase, String orderNoExtra, String drawingNo){
		
		return pidbackMapper.getPidbackDrawing(orderNoBase, orderNoExtra, drawingNo);
	}
	
	public void insPidbackDrawing(PidbackInsForm pidbackInsForm) {
		pidbackMapper.insPidbackDrawing(pidbackInsForm);
	}
	
	public void insPidbackPurchase(PidbackInsForm pidbackInsForm) {
		pidbackMapper.insPidbackPurchase(pidbackInsForm);
	}
	
	
	public PidbackEntry getDetailPidback(Long id) {
		
		return pidbackMapper.getDetailPidback(id);
	}
	
	public List<PidbackComment> getCommentPidback(Long pidBackId){
		return pidbackMapper.getCommentPidback(pidBackId);
	}
	
	public void plusViewUpdate(Long id) {
		
		pidbackMapper.plusViewUpdate(id);
	}
	
	public void addComment(Long id, String sbody, String userId) {
		
		pidbackMapper.addComment(id, sbody, userId);
	}
	
	public void configComment(Long sid, String stitle) {
		
		pidbackMapper.configComment(sid, stitle);
	}
	
	public void delteComment(Long sid) {
		
		pidbackMapper.delteComment(sid);
	}
	
	public void configPidback(Long id, String body, String title) {
		pidbackMapper.configPidback(id, body, title);
	}
	
	public void deletePidback(Long id) {
		pidbackMapper.deletePidback(id);
	}
	
	
	
	
	

}
