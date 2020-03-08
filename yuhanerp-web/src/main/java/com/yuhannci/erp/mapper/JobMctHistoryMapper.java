package com.yuhannci.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.JobMctHistory;

@Mapper
public interface JobMctHistoryMapper {

	public List<JobMctHistory> selectMctHisAll(@Param("orderBy") String orderBy, @Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra, @Param("drawingNo") String drawingNo, @Param("mctDateFrom") String mctDateFrom, @Param("mctDateTo") String mctDateTo);

	public List<JobMctHistory> selectMctHisDrawingUser(@Param("orderBy") String orderBy, @Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra, @Param("drawingNo") String drawingNo, @Param("mctDateFrom") String mctDateFrom, @Param("mctDateTo") String mctDateTo);
	
	public List<JobMctHistory> selectMctHisDrawingMct(@Param("orderBy") String orderBy, @Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra, @Param("drawingNo") String drawingNo, @Param("mctDateFrom") String mctDateFrom, @Param("mctDateTo") String mctDateTo);

	public List<JobMctHistory> selectMctHisOrder(@Param("orderBy") String orderBy, @Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra, @Param("drawingNo") String drawingNo, @Param("mctDateFrom") String mctDateFrom, @Param("mctDateTo") String mctDateTo);

	public List<JobMctHistory> selectMctHisDrawing(@Param("orderBy") String orderBy, @Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra, @Param("drawingNo") String drawingNo, @Param("mctDateFrom") String mctDateFrom, @Param("mctDateTo") String mctDateTo);

	public List<JobMctHistory> selectMctHisUser(@Param("orderBy") String orderBy, @Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra, @Param("drawingNo") String drawingNo, @Param("mctDateFrom") String mctDateFrom, @Param("mctDateTo") String mctDateTo);

	public List<JobMctHistory> selectMctHisMct(@Param("orderBy") String orderBy, @Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra, @Param("drawingNo") String drawingNo, @Param("mctDateFrom") String mctDateFrom, @Param("mctDateTo") String mctDateTo);
}
