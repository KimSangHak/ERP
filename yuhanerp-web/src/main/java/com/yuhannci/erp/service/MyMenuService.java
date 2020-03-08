package com.yuhannci.erp.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuhannci.erp.mapper.MyMenuMapper;
import com.yuhannci.erp.model.db.Customer;
import com.yuhannci.erp.model.db.DeptData;
import com.yuhannci.erp.model.db.LeaveApplication;
import com.yuhannci.erp.model.db.Overwork;
import com.yuhannci.erp.model.db.RoundRobin;
import com.yuhannci.erp.model.db.UserData;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class MyMenuService {
	
	@Autowired MyMenuMapper myMenuMapper;
	
	public List<RoundRobin> roundRobinSelect(String userId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd, String kind) {
		
		return myMenuMapper.roundRobinSelect(userId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
		
	}
	
	public List<LeaveApplication> leaveSelect(String userId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd, String kind) {
		
		return myMenuMapper.leaveSelect(userId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
		
	}
	

	public List<Overwork> overworkSelect(String userId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd, String kind) {
		
		return myMenuMapper.overworkSelect(userId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
		
	}
	
	public List<LeaveApplication> leaveSelectDeleted(String userId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd) {
		
		return myMenuMapper.leaveSelectDeleted(userId, title, convertedDesignDateBegin, convertedDesignDateEnd);
		
	}
	
	public List<Overwork> overworkSelectDeleted(String userId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd) {
		
		return myMenuMapper.overworkSelectDeleted(userId, title, convertedDesignDateBegin, convertedDesignDateEnd);
		
	}
	
	public List<LeaveApplication> leaveSelectDirector(String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd, String kind) {
		
		return myMenuMapper.leaveSelectDirector(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
		
	}
	
	public List<Overwork> overworkSelectDirector(String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd, String kind) {
		
		return myMenuMapper.overworkSelectDirector(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
		
	}
	
	public List<LeaveApplication> leaveSelectDirectorDeleted(String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd) {
		
		return myMenuMapper.leaveSelectDirectorDeleted(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
		
	}
	
	public List<Overwork> overworkSelectDirectorDeleted(String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd) {
		
		return myMenuMapper.overworkSelectDirectorDeleted(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
		
	}
	
	public List<LeaveApplication> leaveSelectFactory(String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd, String kind) {
		
		return myMenuMapper.leaveSelectFactory(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
		
	}
	
	public List<Overwork> overworkSelectFactory(String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd, String kind) {
		
		return myMenuMapper.overworkSelectFactory(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
		
	}
	
	public List<LeaveApplication> leaveSelectFactoryDeleted(String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd) {
		
		return myMenuMapper.leaveSelectFactoryDeleted(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
		
	}
	
	public List<Overwork> overworkSelectFactoryDeleted(String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd) {
		
		return myMenuMapper.overworkSelectFactoryDeleted(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
		
	}
	
	public List<LeaveApplication> leaveSelectManager(String deptCode ,String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd, String kind) {
		
		return myMenuMapper.leaveSelectManager(deptCode, requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
		
	}
	
	public List<Overwork> overworkSelectManager(String deptCode ,String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd, String kind) {
		
		return myMenuMapper.overworkSelectManager(deptCode, requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
		
	}
	
	public List<LeaveApplication> leaveSelectManagerDeleted(String deptCode ,String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd) {
		
		return myMenuMapper.leaveSelectManagerDeleted(deptCode, requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
		
	}
	
	public List<Overwork> overworkSelectManagerDeleted(String deptCode ,String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd) {
		
		return myMenuMapper.overworkSelectManagerDeleted(deptCode, requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
		
	}
	
	public List<LeaveApplication> leaveSelectCEO(String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd, String kind) {
		
		return myMenuMapper.leaveSelectCEO(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
		
	}
	
	public List<Overwork> overworkSelectCEO(String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd, String kind) {
		
		return myMenuMapper.overworkSelectCEO(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
		
	}
	
	public List<LeaveApplication> leaveSelectComplet(String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd) {
		
		return myMenuMapper.leaveSelectComplet(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
		
	}
	
	public List<Overwork> overworkSelectComplet(String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd) {
		
		return myMenuMapper.overworkSelectComplet(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
		
	}
	
	public List<LeaveApplication> leaveSelectCEODelete(String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd) {
		
		return myMenuMapper.leaveSelectCEODelete(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
		
	}
	
	public List<Overwork> overworkSelectCEODelete(String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd) {
		
		return myMenuMapper.overworkSelectCEODelete(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
		
	}
	
	public List<RoundRobin> roundRobinSelectDeleted(String userId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd) {
		
		return myMenuMapper.roundRobinSelectDeleted(userId, title, convertedDesignDateBegin, convertedDesignDateEnd);
		
	}
	
	public List<RoundRobin> roundRobinSelectDeletedAll(String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd) {
		
		return myMenuMapper.roundRobinSelectDeletedAll(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
		
	}
	
	public List<RoundRobin> roundRobinSelectDeletedManager(String deptCode ,String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd) {
		
		return myMenuMapper.roundRobinSelectDeletedManager(deptCode, requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
		
	}
	
	public List<RoundRobin> purchaseroundRobinSelect(String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd) {
		
		return myMenuMapper.purchaseroundRobinSelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
		
	}
	
	public List<Customer> selectAllCustomer(){
		return myMenuMapper.selectAllCustomer();
	}
	
	public void insertRobin(RoundRobin RoundRobin) {
		myMenuMapper.insertRobin(RoundRobin);
	}
	
	public void insertRobinToM(RoundRobin RoundRobin) {
		myMenuMapper.insertRobinToM(RoundRobin);
	}
	
	public RoundRobin detailRobinOne(String roundNo) {
		return myMenuMapper.detailRobinOne(roundNo);
	}
	
	public List<RoundRobin> detailRobinAll(String roundNo){
		return myMenuMapper.detailRobinAll(roundNo);
	}
	
	public void delteRobin(String deleteReason, String roundNo, String deleteUserId) {
		myMenuMapper.delteRobin(deleteReason, roundNo, deleteUserId);
	}
	
	public void delteLeave(String deleteReason, Long id, String deleteUserId) {
		myMenuMapper.delteLeave(deleteReason, id, deleteUserId);
	}
	
	public void delteOverwork(String deleteReason, Long id, String deleteUserId) {
		myMenuMapper.delteOverwork(deleteReason, id, deleteUserId);
	}
	
	public String[] managerDeptCode(String userId) {
		return myMenuMapper.managerDeptCode(userId);
	}
	
	public List<RoundRobin> roundRobinManagerSelect(String[] DeptCode, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd){
		return myMenuMapper.roundRobinManagerSelect(DeptCode, title, convertedDesignDateBegin, convertedDesignDateEnd);
	}
	
	public List<RoundRobin> roundRobinPurchaseSelect(String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd){
		return myMenuMapper.roundRobinPurchaseSelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
	}
	
	public List<LeaveApplication> leaveManagerSelect(String[] DeptCode, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd){
		return myMenuMapper.leaveManagerSelect(DeptCode, title, convertedDesignDateBegin, convertedDesignDateEnd);
	}
	
	public List<Overwork> overworkManagerSelect(String[] DeptCode, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd){
		return myMenuMapper.overworkManagerSelect(DeptCode, title, convertedDesignDateBegin, convertedDesignDateEnd);
	}
	
	public List<RoundRobin> roundRobinDirectoSelect(String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd){
		return myMenuMapper.roundRobinDirectoSelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
	}
	
	public List<LeaveApplication> leaveDirectoSelect(String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd){
		return myMenuMapper.leaveDirectoSelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
	}
	
	public List<Overwork> overworkDirectoSelect(String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd){
		return myMenuMapper.overworkDirectoSelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
	}
	
	public List<LeaveApplication> leaveFactorySelect(String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd){
		return myMenuMapper.leaveFactorySelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
	}
	
	public List<Overwork> overworkFactorySelect(String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd){
		return myMenuMapper.overworkFactorySelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
	}
	
	public List<RoundRobin> roundRobinCEOSelect(String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd){
		return myMenuMapper.roundRobinCEOSelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
	}
	
	public List<LeaveApplication> leaveCEOSelect(String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd){
		return myMenuMapper.leaveCEOSelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
	}
	
	public List<Overwork> overworkCEOSelect(String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd){
		return myMenuMapper.overworkCEOSelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
	}
	
	public List<RoundRobin> roundRobinAlltoSelect(String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd){
		return myMenuMapper.roundRobinAlltoSelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
	}
	
	public String directorYN(String userId) {
		return myMenuMapper.directorYN(userId);
		
	}
	
	public String ceoYN(String userId) {
		return myMenuMapper.ceoYN(userId);
		
	}
	
	public void conformM(String signPath, String roundNo) {
		myMenuMapper.conformM(signPath, roundNo);
	}
	
	public void leaveConformM(String signPath, Long id) {
		myMenuMapper.leaveConformM(signPath, id);
	}
	
	public void overworkConformM(String signPath, Long id) {
		myMenuMapper.overworkConformM(signPath, id);
	}
	
	public void conformD(String signPath, String roundNo, String roundKind) {
		myMenuMapper.conformD(signPath, roundNo, roundKind);
	}
	
	public void conformDAll(String signPath1, String signPath2, String roundNo, String roundKind) {
		myMenuMapper.conformDAll(signPath1, signPath2, roundNo, roundKind);
	}
	
	public void conformDtoR(String signPath, String roundNo, String roundKind) {
		myMenuMapper.conformDtoR(signPath, roundNo, roundKind);
	}
	
	public void leaveconformD(String signPath, Long id) {
		myMenuMapper.leaveconformD(signPath, id);
	}
	
	public void overworkconformD(String signPath, Long id) {
		myMenuMapper.overworkconformD(signPath, id);
	}
	
	public void leaveconformDtoR(String signPath, Long id) {
		myMenuMapper.leaveconformDtoR(signPath, id);
	}
	
	public void overworkconformDtoR(String signPath, Long id) {
		myMenuMapper.overworkconformDtoR(signPath, id);
	}
	
	
	public void conformCEOFromJ(String signPath1, String signPath2, String roundNo) {
		myMenuMapper.conformCEOFromJ(signPath1, signPath2, roundNo);
	}
	
	public void conformCEO(String signPath, String roundNo) {
		myMenuMapper.conformCEO(signPath, roundNo);
	}
	
	public void leaveconformCEO(String signPath, Long id) {
		myMenuMapper.leaveconformCEO(signPath, id);
	}
	
	public void overworkconformCEO(String signPath, Long id) {
		myMenuMapper.overworkconformCEO(signPath, id);
	}
	
	public void conformCEOtoR(String signPath, String roundNo) {
		myMenuMapper.conformCEOtoR(signPath, roundNo);
	}
	
	public void leaveconformCEOtoR(String signPath, Long id) {
		myMenuMapper.leaveconformCEOtoR(signPath, id);
	}
	
	public void overworkconformCEOtoR(String signPath, Long id) {
		myMenuMapper.overworkconformCEOtoR(signPath, id);
	}
	
	public void conformCEOtoM(String signPath, String roundNo) {
		myMenuMapper.conformCEOtoM(signPath, roundNo);
	}
	
	public void leaveconformCEOtoM(String signPath, Long id) {
		myMenuMapper.leaveconformCEOtoM(signPath, id);
	}
	
	public void overworkconformCEOtoM(String signPath, Long id) {
		myMenuMapper.overworkconformCEOtoM(signPath, id);
	}
	
	public void conformAllToM(String signPath, String roundNo) {
		myMenuMapper.conformAllToM(signPath, roundNo);
	}
	
	public void conformAllToAll(String signPath, String roundNo) {
		myMenuMapper.conformAllToAll(signPath, roundNo);
	}
	
	public void conformPToAll(String signPath1, String signPath2, String roundNo) {
		myMenuMapper.conformPToAll(signPath1, signPath2, roundNo);
	}
	
	public void conformPToDo(String signPath, String roundNo) {
		myMenuMapper.conformPToDo(signPath, roundNo);
	}
	
	public void conformPToAllround(String signPath1, String signPath2, String roundNo) {
		myMenuMapper.conformPToAllround(signPath1, signPath2, roundNo);
	}
	
	public void conformPToAllroundStageM(String signPath1, String signPath2, String roundNo) {
		myMenuMapper.conformPToAllroundStageM(signPath1, signPath2, roundNo);
	}
	
	public void setPriceForPurchaseConfrom(Long id, int price) {
		myMenuMapper.setPriceForPurchaseConfrom(id, price);
		
	}
	public void roundRobinStageF(String roundNo) {
		myMenuMapper.roundRobinStageF(roundNo);
	}
	
	public Long getConsumablesOrderId() {
		return myMenuMapper.getConsumablesOrderId();
	}
	
	public String getRoundrobinStage(String roundNo) {
		return myMenuMapper.getRoundrobinStage(roundNo);
	}
	
	public String getLeaveStage(Long id) {
		return myMenuMapper.getLeaveStage(id);
	}
	
	public String getOverworkStage(Long id) {
		return myMenuMapper.getOverworkStage(id);
	}
	
	public String isManagerRoundrobin(String usrId) {
		return myMenuMapper.isManagerRoundrobin(usrId);
	}
	
	public String isFactoryRoundrobin(String usrId) {
		return myMenuMapper.isFactoryRoundrobin(usrId);
	}
	
	public String isDirectorRoundrobin(String usrId) {
		return myMenuMapper.isDirectorRoundrobin(usrId);
	}
	
	public String isCEORoundrobin(String usrId) {
		return myMenuMapper.isCEORoundrobin(usrId);
	}
	
	public String isPQroundrobin(String usrId) {
		return myMenuMapper.isPQroundrobin(usrId);
	}
	
	public String usrNameForIdroundrobin(String usrName) {
		return myMenuMapper.usrNameForIdroundrobin(usrName);
	}
	
	public List<RoundRobin> roundRobinSelectDirector(String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd, String kind) {
		
		return myMenuMapper.roundRobinSelectDirector(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
		
	}
	
	
	
	public List<RoundRobin> roundRobinSelectManager(String deptCode ,String requestId, String title, Date convertedDesignDateBegin, Date convertedDesignDateEnd, String kind) {
		
		return myMenuMapper.roundRobinSelectManager(deptCode, requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
		
	}
	
	public String deptCodeFromRoundrobin(String usrId) {
		return myMenuMapper.deptCodeFromRoundrobin(usrId);
	}
	
	public String requestUsrIdRounrobin(String roundNo) {
		return myMenuMapper.requestUsrIdRounrobin(roundNo);
	}
	
	public String requestUsrIdLeave(Long id) {
		return myMenuMapper.requestUsrIdLeave(id);
	}
	
	public String requestUsrIdOverwork(Long id) {
		return myMenuMapper.requestUsrIdOverwork(id);
	}
	
	public int leaveCountHour(String usrId) {
		return myMenuMapper.leaveCountHour(usrId);
	}
	
	public int leaveCountHourReal(String usrId) {
		return myMenuMapper.leaveCountHourReal(usrId);
	}
	
	@Transactional
	public Long insertLeaveApplication(LeaveApplication leaveApplication) {
		myMenuMapper.insertLeaveApplication(leaveApplication);
		
		Long leaveId = leaveApplication.getId();
		return leaveId;
	}
	
	@Transactional
	public Long insertLeaveApplicationM(LeaveApplication leaveApplication) {
		myMenuMapper.insertLeaveApplicationM(leaveApplication);
		
		Long leaveId = leaveApplication.getId();
		return leaveId;
	}
	
	@Transactional
	public Long insertLeaveApplicationJ(LeaveApplication leaveApplication) {
		myMenuMapper.insertLeaveApplicationJ(leaveApplication);
		
		Long leaveId = leaveApplication.getId();
		return leaveId;
	}
	
	public void insertLeaveApplicationC(LeaveApplication leaveApplication) {
		myMenuMapper.insertLeaveApplicationC(leaveApplication);
	}
	
	public LeaveApplication detailLeaveOne(Long id) {
		
		return myMenuMapper.detailLeaveOne(id);
	}
	
	public Overwork detailoverworkOne(Long id) {
		
		return myMenuMapper.detailoverworkOne(id);
	}
	
	public String leaveRequestUsrIdSelect(Long id) {
		return myMenuMapper.leaveRequestUsrIdSelect(id);
	}
	
	public String leaveKindSelect(Long id) {
		return myMenuMapper.leaveKindSelect(id);
	}
	
	public int leaveDateSelect(Long id) {
		return myMenuMapper.leaveDateSelect(id);
	}
	public int leaveHourSelect(Long id) {
		return myMenuMapper.leaveHourSelect(id);
	}
	
	public int usrVacationHour(String usrId) {
		return myMenuMapper.usrVacationHour(usrId);
	}
	
	public int usrVacationHourReal(String usrId) {
		return myMenuMapper.usrVacationHourReal(usrId);
	}
	
	public void setVacationusr(String usrId, int result) {
		myMenuMapper.setVacationusr(usrId, result);
	}
	
	public void setVacationusrReal(String usrId, int result) {
		myMenuMapper.setVacationusrReal(usrId, result);
	}
	
	
	@Transactional
	public Long insertoverworkA(Overwork overwork) {
		myMenuMapper.insertoverworkA(overwork);
		
		Long overworkId = overwork.getId();
		return overworkId;
	}
	
	
	@Transactional
	public Long insertoverworkAM(Overwork overwork) {
		myMenuMapper.insertoverworkAM(overwork);
		
		Long overworkId = overwork.getId();
		return overworkId;
	}
	
	
	@Transactional
	public Long insertoverworkAJ(Overwork overwork) {
		myMenuMapper.insertoverworkAJ(overwork);
		
		Long overworkId = overwork.getId();
		return overworkId;
	}
	
	public void insertoverworkAC(Overwork overwork) {
		myMenuMapper.insertoverworkAC(overwork);
	}
	
	public List<UserData> getUsrAllDatafromeditUsrleave(){
		return myMenuMapper.getUsrAllDatafromeditUsrleave();
	}
	
	public void roundrobinMoveToM(String roundNo) {
		myMenuMapper.roundrobinMoveToM(roundNo);
	}
	
	public String usrNameForInsNoticeMessage(String usrId) {
		return myMenuMapper.usrNameForInsNoticeMessage(usrId);
	}
	
	public String usrENGNameForInsNoticeMessage(String usrId) {
		return myMenuMapper.usrENGNameForInsNoticeMessage(usrId);
	}
	
	public String leaveKindDetpfromNoticeMessage(Long id) {
		
		return myMenuMapper.leaveKindDetpfromNoticeMessage(id);
		
	}
	
	public String leaveRequestUsrfromNoticeMessage(Long id) {
		
		return myMenuMapper.leaveRequestUsrfromNoticeMessage(id);
		
	}
	
	public String overWorkRequestUsrfromNoticeMessage(Long id) {
		
		return myMenuMapper.overWorkRequestUsrfromNoticeMessage(id);
		
	}
	
	public String overWorkDetpfromNoticeMessage(Long id) {
		
		return myMenuMapper.overWorkDetpfromNoticeMessage(id);
		
	}
	
	public int sumPriceFromRoundNumNoticeMessage(String roundNo) {
		return myMenuMapper.sumPriceFromRoundNumNoticeMessage(roundNo);
	}
	
	public String orderNoFullNameFromNoticeMessage(Long id) {
		return myMenuMapper.orderNoFullNameFromNoticeMessage(id);
	}
	
	public Long orderIdFromNoticeMessageFromRound(String roundNo) {
		
		return myMenuMapper.orderIdFromNoticeMessageFromRound(roundNo);
	}
	
	public String completYNfromLeaveConfromCEO(Long id) {
		return myMenuMapper.completYNfromLeaveConfromCEO(id);
	}
	
	
	public boolean isUpdatePossible(Long id) {
		
		boolean result = false;
		
		if(myMenuMapper.isUpdatePossible(id)==1) {
			result = true;
		}
		
		return result;
		
	}
	
	
	public String leaveGetStage(Long id) {
		
		return myMenuMapper.leaveGetStage(id);
	}
	
	public int leaveGetRequestDate(Long id) {
		return myMenuMapper.leaveGetRequestDate(id);
	}
	
	public int leaveGetRequestHour(Long id) {
		return myMenuMapper.leaveGetRequestHour(id);
	}
	
	public String leaveGettitle(Long id) {
		return myMenuMapper.leaveGettitle(id);
	}
	
	public void addUserVacation(String userId, int leaveDate) {
		myMenuMapper.addUserVacation(userId, leaveDate);
	}
	
	public void vacationDeletedAndChange(Long id) {
		myMenuMapper.vacationDeletedAndChange(id);
	}
	
	
	

}
