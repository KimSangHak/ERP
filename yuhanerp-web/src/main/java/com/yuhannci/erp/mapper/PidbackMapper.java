package com.yuhannci.erp.mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.PibackPurchase;
import com.yuhannci.erp.model.PidbackDrawing;
import com.yuhannci.erp.model.PidbackInsForm;
import com.yuhannci.erp.model.PidbackSearchForm;
import com.yuhannci.erp.model.db.PidbackComment;
import com.yuhannci.erp.model.db.PidbackEntry;


@Mapper
public interface PidbackMapper {
	
	public Integer selectPidbackEntryCount(PidbackSearchForm form);
	
	public List<PidbackEntry> selectPidbackEntry(PidbackSearchForm form);
	
	public List<PibackPurchase> getPidbackPurchase(@Param("modelNo") String modelNo, @Param("description") String description);
	
	public List<PidbackDrawing> getPidbackDrawing(@Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra, @Param("drawingNo") String drawingNo);
	
	public void insPidbackDrawing(PidbackInsForm pidbackInsForm);
	
	public void insPidbackPurchase(PidbackInsForm pidbackInsForm);
	
	public PidbackEntry getDetailPidback(Long id);
	
	public List<PidbackComment> getCommentPidback(Long pidBackId);
	
	public void plusViewUpdate(Long id);
	
	public void addComment(@Param("id") Long id, @Param("sbody") String sbody, @Param("userId") String userId);
	
	public void configComment(@Param("sid") Long sid, @Param("stitle") String stitle);
	
	public void delteComment(Long sid);
	
	public void configPidback(@Param("id") Long id, @Param("body") String body, @Param("title") String title);
	
	public void deletePidback(Long id);
	
	
	

}
