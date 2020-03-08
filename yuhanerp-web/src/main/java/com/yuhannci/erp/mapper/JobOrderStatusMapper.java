package com.yuhannci.erp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface JobOrderStatusMapper {
	
	int assembleStart(@Param("id") Long id, @Param("setuser") String setuser, @Param("updateuser") String updateuser);
	int assembleHoldSet(@Param("id") Long id, @Param("atype") String atype, @Param("reason") String reason, @Param("updateuser") String updateuser);
	int assembleCompleteSet(@Param("id") Long id, @Param("updateuser") String updateuser);
	int assembleUpdateProg(@Param("id") Long id, @Param("assambleProg") Integer assambleProg, @Param("updateuser") String updateuser);
	
	int setupStart(@Param("id") Long id, @Param("setup_id") Long setup_id, @Param("updateuser") String updateuser);

	int programStart(@Param("id") Long id, @Param("setuser") String setuser, @Param("updateuser") String updateuser);
	int programHoldSet(@Param("id") Long id, @Param("atype") String atype, @Param("reason") String reason, @Param("updateuser") String updateuser);
	int programCompleteSet(@Param("id") Long id, @Param("updateuser") String updateuser);
	int programUpdateProg(@Param("id") Long id, @Param("progValue") Integer progValue, @Param("updateuser") String updateuser);
	
	int designStart(@Param("id") Long id, @Param("setuser") String setuser, @Param("updateuser") String updateuser);
	int designHoldSet(@Param("id") Long id, @Param("atype") String atype, @Param("reason") String reason, @Param("updateuser") String updateuser);
	int designCompleteSet(@Param("id") Long id, @Param("updateuser") String updateuser);
	int designUpdateProg(@Param("id") Long id, @Param("assambleProg") Integer assambleProg, @Param("updateuser") String updateuser);
	int insertWageRate(@Param("id") Long id, @Param("workDay") int workDay, @Param("designUserId") String designUserId, @Param("assemblyUserId") String assemblyUserId, @Param("programUserId") String programUserId, @Param("workKind") String workKind);
	public String getStartDateOrder(Long id);
	public String getEndtDateOrder(Long id);
	public String getStartDateOrderA(Long id);
	public String getEndtDateOrderA(Long id);
	public String getStartDateOrderP(Long id);
	public String getEndtDateOrderP(Long id);
}
