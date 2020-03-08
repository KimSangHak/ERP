package com.yuhannci.erp.service;

import java.util.HashMap;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuhannci.erp.mapper.JobOrderChangeHistoryMapper;
import com.yuhannci.erp.model.JobOrderModificationEntry;
import com.yuhannci.erp.model.JobStageEnum;
import com.yuhannci.erp.model.JobTypeEnum;
import com.yuhannci.erp.model.OrderTypeEnum;
import com.yuhannci.erp.model.SourceCategoryEnum;
import com.yuhannci.erp.model.db.JobOrderModificationHistory;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobOrderChangeHistoryService {
	
	@Autowired JobOrderChangeHistoryMapper jobOrderChangeHistoryMapper;
	
	public enum StatusEnum {
		Delivered("배송"),
		Finished("완료");
		
		String label;
		StatusEnum(String label){
			this.label = label;
		}
		
		@Override
		public String toString(){
			return label;
		}
	}
	
	// 해당 설비/치공구 작업이 완료
	public void addFinishStatus(JobStageEnum orderType, Long id, String finishedUserId){
		
		addStatusChange(orderType, id, finishedUserId, StatusEnum.Finished);
		
	}
	
	// 상태 변경
	public void addStatusChange(JobStageEnum orderType, Long id, String userId, StatusEnum status){
		
	}
	
	// 삭제 처리
	public void addDeleteStatus(JobStageEnum orderType, Long id, String finishedUserId){
		
	}
	
	public List<JobOrderModificationEntry> loadChangeHistory(long jobOrderid){
		List<JobOrderModificationEntry> res = jobOrderChangeHistoryMapper.selectChangeHistory(jobOrderid);
		return res;
	}
	
	// 작업 지시 변경 내역 저장
	@Transactional
	public void addJobOrderChangeHistory(String who, Long jobOrderId, JobStageEnum jobStageEnum, String jobOrderType, SourceCategoryEnum sourceCategory, HashMap<String, SimpleEntry<String, String>> changed) throws Exception {
		
		if(jobOrderType == null)
			throw new Exception("order-type is null");
		if(sourceCategory == null)
			throw new Exception("source-category is null");
		
		// 변경 내역 추가
		Long newId = 0L;
		JobOrderModificationHistory ret = new JobOrderModificationHistory();
		jobOrderChangeHistoryMapper.insertHistory(jobOrderId, jobOrderType.toString(), sourceCategory.toString(), "CHANGE", who, ret);
		newId = ret.getId();
		log.info("변경 ID = " + newId);
		
		// 변경 전/후 데이터 기록
		SimpleEntry<String, String> val;
		for(String key : changed.keySet()){
			val = changed.get(key);
			jobOrderChangeHistoryMapper.insertValueChangeHistory(newId, key, val.getKey(), val.getValue());
		}		
		
	}
}
