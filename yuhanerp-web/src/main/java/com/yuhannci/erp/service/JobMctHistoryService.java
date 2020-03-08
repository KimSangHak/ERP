package com.yuhannci.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhannci.erp.mapper.JobMctHistoryMapper;
import com.yuhannci.erp.model.JobMctHistory;

@Service
public class JobMctHistoryService {

	@Autowired JobMctHistoryMapper jobMctHisMapper;
	
	public List<JobMctHistory> getMctHistoryAll(String orderBy, String orderNoBase, String orderNoExtra, String drawingNo, String mctDateFrom, String mctDateTo){
		
		return jobMctHisMapper.selectMctHisAll(orderBy, orderNoBase, orderNoExtra, drawingNo, mctDateFrom, mctDateTo);
	}

	public List<JobMctHistory> getMctHistoryDwUser(String orderBy, String orderNoBase, String orderNoExtra, String drawingNo, String mctDateFrom, String mctDateTo){
		
		return jobMctHisMapper.selectMctHisDrawingUser(orderBy, orderNoBase, orderNoExtra, drawingNo, mctDateFrom, mctDateTo);
	}
	
	public List<JobMctHistory> getMctHistoryDwMct(String orderBy, String orderNoBase, String orderNoExtra, String drawingNo, String mctDateFrom, String mctDateTo){
		
		return jobMctHisMapper.selectMctHisDrawingMct(orderBy, orderNoBase, orderNoExtra, drawingNo, mctDateFrom, mctDateTo);
	}

	public List<JobMctHistory> getMctHistoryOrder(String orderBy, String orderNoBase, String orderNoExtra, String drawingNo, String mctDateFrom, String mctDateTo){
		
		return jobMctHisMapper.selectMctHisOrder(orderBy, orderNoBase, orderNoExtra, drawingNo, mctDateFrom, mctDateTo);
	}

	public List<JobMctHistory> getMctHistoryDrawing(String orderBy, String orderNoBase, String orderNoExtra, String drawingNo, String mctDateFrom, String mctDateTo){
		
		return jobMctHisMapper.selectMctHisDrawing(orderBy, orderNoBase, orderNoExtra, drawingNo, mctDateFrom, mctDateTo);
	}

	public List<JobMctHistory> getMctHistoryUser(String orderBy, String orderNoBase, String orderNoExtra, String drawingNo, String mctDateFrom, String mctDateTo){
		
		return jobMctHisMapper.selectMctHisUser(orderBy, orderNoBase, orderNoExtra, drawingNo, mctDateFrom, mctDateTo);
	}

	public List<JobMctHistory> getMctHistoryMct(String orderBy, String orderNoBase, String orderNoExtra, String drawingNo, String mctDateFrom, String mctDateTo){
		
		return jobMctHisMapper.selectMctHisMct(orderBy, orderNoBase, orderNoExtra, drawingNo, mctDateFrom, mctDateTo);
	}
}
