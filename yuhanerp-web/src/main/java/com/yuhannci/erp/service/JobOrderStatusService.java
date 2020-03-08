package com.yuhannci.erp.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuhannci.erp.mapper.JobOrderStatusMapper;

@Service
public class JobOrderStatusService {

	@Autowired JobOrderStatusMapper jobStatusMapper;
	
	@Transactional
	public void makeAssemblePlay(Long id, String setuser, String updateuser){
		jobStatusMapper.assembleStart(id, setuser, updateuser);
	}
	
	@Transactional
	public void makeAssembleHold(Long id, String atype, String reason, String updateuser){
		jobStatusMapper.assembleHoldSet(id, atype, reason, updateuser);
	}
	
	@Transactional
	public void makeAssembleComplete(Long id, String updateuser){
		jobStatusMapper.assembleCompleteSet(id, updateuser);
	}
	
	@Transactional
	public void updateAssembleProg(Long id, Integer assambleProg, String updateuser){
		jobStatusMapper.assembleUpdateProg(id, assambleProg, updateuser);
	}

	@Transactional
	public void makeSetupStart(Long id, Long setup_id, String updateuser){
		jobStatusMapper.setupStart(id, setup_id, updateuser);
	}
	
	@Transactional
	public void makeProgramPlay(Long id, String setuser, String updateuser){
		jobStatusMapper.programStart(id, setuser, updateuser);
	}

	@Transactional
	public void makeProgramHold(Long id, String atype, String reason, String updateuser){
		jobStatusMapper.programHoldSet(id, atype, reason, updateuser);
	}
	
	@Transactional
	public void makeProgramComplete(Long id, String updateuser){
		jobStatusMapper.programCompleteSet(id, updateuser);
	}
	
	@Transactional
	public void updateProgramProg(Long id, Integer progValue, String updateuser){
		jobStatusMapper.programUpdateProg(id, progValue, updateuser);
	}

	@Transactional
	public void makeDesginPlay(Long id, String setuser, String updateuser){
		jobStatusMapper.designStart(id, setuser, updateuser);
	}
	@Transactional
	public void makeDesginHold(Long id, String atype, String reason, String updateuser){
		jobStatusMapper.designHoldSet(id, atype, reason, updateuser);
	}
	
	@Transactional
	public void makeDesginComplete(Long id, String updateuser){
		jobStatusMapper.designCompleteSet(id, updateuser);
	}
	
	@Transactional
	public void updateDesginProg(Long id, Integer assambleProg, String updateuser){
		jobStatusMapper.designUpdateProg(id, assambleProg, updateuser);
	}
	
	@Transactional
	public void insertWageRate(Long id, int workDay, String designUserId, String assemblyUserId, String programUserId, String workKind){
		jobStatusMapper.insertWageRate(id, workDay, designUserId, assemblyUserId, programUserId, workKind);
	}
	
	public String getStartDateOrder(Long id) {
		return jobStatusMapper.getStartDateOrder(id);
	}
	
	public String getEndtDateOrder(Long id) {
		return jobStatusMapper.getEndtDateOrder(id);
	}
	
	public String getStartDateOrderA(Long id) {
		return jobStatusMapper.getStartDateOrderA(id);
	}
	
	public String getEndtDateOrderA(Long id) {
		return jobStatusMapper.getEndtDateOrderA(id);
	}
	
	public String getStartDateOrderP(Long id) {
		return jobStatusMapper.getStartDateOrderP(id);
	}
	
	public String getEndtDateOrderP(Long id) {
		return jobStatusMapper.getEndtDateOrderP(id);
	}
	
}
