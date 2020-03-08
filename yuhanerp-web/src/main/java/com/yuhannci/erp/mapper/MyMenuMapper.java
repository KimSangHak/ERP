package com.yuhannci.erp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.db.Customer;
import com.yuhannci.erp.model.db.LeaveApplication;
import com.yuhannci.erp.model.db.Overwork;
import com.yuhannci.erp.model.db.RoundRobin;
import com.yuhannci.erp.model.db.UserData;

@Mapper
public interface MyMenuMapper {
	
	public List<RoundRobin> roundRobinSelect(@Param("userId") String userId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd, @Param("kind") String kind);
	
	public List<RoundRobin> roundRobinSelectDeleted(@Param("userId") String userId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<RoundRobin> roundRobinSelectDeletedAll(@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<RoundRobin> roundRobinSelectDeletedManager(@Param("deptCode") String deptCode, @Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<RoundRobin> purchaseroundRobinSelect(@Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<Customer> selectAllCustomer();
	
	public void insertRobin(RoundRobin RoundRobin);
	
	public void insertRobinToM(RoundRobin RoundRobin);
	
	public RoundRobin detailRobinOne(String roundNo);
	
	public List<RoundRobin> detailRobinAll(String roundNo);
	
	public void delteRobin(@Param("deleteReason") String deleteReason, @Param("roundNo") String roundNo, @Param("deleteUserId") String deleteUserId);
	
	public void delteLeave(@Param("deleteReason") String deleteReason, @Param("id") Long id, @Param("deleteUserId") String deleteUserId);
	
	public void delteOverwork(@Param("deleteReason") String deleteReason, @Param("id") Long id, @Param("deleteUserId") String deleteUserId);
	
	public String[] managerDeptCode(String userId);
	
	public List<RoundRobin> roundRobinManagerSelect(@Param("DeptCode") String[] DeptCode, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<LeaveApplication> leaveManagerSelect(@Param("DeptCode") String[] DeptCode, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<Overwork> overworkManagerSelect(@Param("DeptCode") String[] DeptCode, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<RoundRobin> roundRobinDirectoSelect(@Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<RoundRobin> roundRobinPurchaseSelect(@Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<LeaveApplication> leaveDirectoSelect(@Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<Overwork> overworkDirectoSelect(@Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<LeaveApplication> leaveFactorySelect(@Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<Overwork> overworkFactorySelect(@Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<RoundRobin> roundRobinCEOSelect(@Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<LeaveApplication> leaveCEOSelect(@Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<Overwork> overworkCEOSelect(@Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<RoundRobin> roundRobinAlltoSelect(@Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public String directorYN(String userId);
	
	public String ceoYN(String userId);
	
	public void conformM(@Param("signPath") String signPath, @Param("roundNo") String roundNo);
	
	public void conformD(@Param("signPath") String signPath, @Param("roundNo") String roundNo, @Param("roundKind") String roundKind);
	
	public void conformDAll(@Param("signPath1") String signPath1, @Param("signPath2") String signPath2, @Param("roundNo") String roundNo, @Param("roundKind") String roundKind);
	
	public void conformDtoR(@Param("signPath") String signPath, @Param("roundNo") String roundNo, @Param("roundKind") String roundKind);
	
	public void leaveconformD(@Param("signPath") String signPath, @Param("id") Long id);
	
	public void overworkconformD(@Param("signPath") String signPath, @Param("id") Long id);
	
	public void leaveconformDtoR(@Param("signPath") String signPath, @Param("id") Long id);
	
	public void overworkconformDtoR(@Param("signPath") String signPath, @Param("id") Long id);
	
	public void conformCEOFromJ(@Param("signPath1") String signPath1, @Param("signPath2") String signPath2, @Param("roundNo") String roundNo);
	
	public void conformCEO(@Param("signPath") String signPath, @Param("roundNo") String roundNo);
	
	public void leaveconformCEO(@Param("signPath") String signPath, @Param("id") Long id);
	
	public void overworkconformCEO(@Param("signPath") String signPath, @Param("id") Long id);
	
	public void conformCEOtoR(@Param("signPath") String signPath, @Param("roundNo") String roundNo);
	
	public void leaveconformCEOtoR(@Param("signPath") String signPath, @Param("id") Long id);
	
	public void overworkconformCEOtoR(@Param("signPath") String signPath, @Param("id") Long id);
	
	public void conformCEOtoM(@Param("signPath") String signPath, @Param("roundNo") String roundNo);
	
	public void leaveconformCEOtoM(@Param("signPath") String signPath, @Param("id") Long id);
	
	public void overworkconformCEOtoM(@Param("signPath") String signPath, @Param("id") Long id);
	
	public void conformAllToM(@Param("signPath") String signPath, @Param("roundNo") String roundNo);
	
	public void conformAllToAll(@Param("signPath") String signPath, @Param("roundNo") String roundNo);
	
	public void conformPToAll(@Param("signPath1") String signPath1, @Param("signPath2") String signPath2, @Param("roundNo") String roundNo);
	
	public void conformPToAllround(@Param("signPath1") String signPath1, @Param("signPath2") String signPath2, @Param("roundNo") String roundNo);
	
	public void conformPToAllroundStageM(@Param("signPath1") String signPath1, @Param("signPath2") String signPath2, @Param("roundNo") String roundNo);
	
	public void conformPToDo(@Param("signPath") String signPath, @Param("roundNo") String roundNo);
	
	public void setPriceForPurchaseConfrom(@Param("id") Long id, @Param("price") int price);
	
	public void roundRobinStageF(String roundNo);
	
	public Long getConsumablesOrderId();
	
	public String getRoundrobinStage(String roundNo);
	
	public String getLeaveStage(Long id);
	
	public String getOverworkStage(Long id);
	
	public String isManagerRoundrobin(String usrId);
	
	public String isFactoryRoundrobin(String usrId);
	
	public String isDirectorRoundrobin(String usrId);
	
	public String isCEORoundrobin(String usrId);
	
	public String isPQroundrobin(String usrId);
	
	public String usrNameForIdroundrobin(String usrName);
	
	public List<RoundRobin> roundRobinSelectDirector(@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd, @Param("kind") String kind);
	
	public List<RoundRobin> roundRobinSelectManager(@Param("deptCode") String deptCode ,@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd, @Param("kind") String kind);
	
	public String deptCodeFromRoundrobin(String usrId);
	
	public String requestUsrIdRounrobin(String roundNo);
	
	public List<LeaveApplication> leaveSelect(@Param("userId") String userId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd, @Param("kind") String kind);
	
	public List<Overwork> overworkSelect(@Param("userId") String userId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd, @Param("kind") String kind);
	
	public List<LeaveApplication> leaveSelectDeleted(@Param("userId") String userId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<Overwork> overworkSelectDeleted(@Param("userId") String userId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<LeaveApplication> leaveSelectDirector(@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd, @Param("kind") String kind);
	
	public List<Overwork> overworkSelectDirector(@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd, @Param("kind") String kind);
	
	public List<LeaveApplication> leaveSelectDirectorDeleted(@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<Overwork> overworkSelectDirectorDeleted(@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<LeaveApplication> leaveSelectFactory(@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd, @Param("kind") String kind);
	
	public List<Overwork> overworkSelectFactory(@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd, @Param("kind") String kind);
	
	public List<LeaveApplication> leaveSelectFactoryDeleted(@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<Overwork> overworkSelectFactoryDeleted(@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<LeaveApplication> leaveSelectManager(@Param("deptCode") String deptCode ,@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd, @Param("kind") String kind);
	
	public List<Overwork> overworkSelectManager(@Param("deptCode") String deptCode ,@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd, @Param("kind") String kind);
	
	public List<LeaveApplication> leaveSelectManagerDeleted(@Param("deptCode") String deptCode ,@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<Overwork> overworkSelectManagerDeleted(@Param("deptCode") String deptCode ,@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<LeaveApplication> leaveSelectCEO(@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd, @Param("kind") String kind);
	
	public List<Overwork> overworkSelectCEO(@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd, @Param("kind") String kind);
	
	public List<LeaveApplication> leaveSelectComplet(@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<Overwork> overworkSelectComplet(@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<LeaveApplication> leaveSelectCEODelete(@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public List<Overwork> overworkSelectCEODelete(@Param("requestId") String requestId, @Param("title") String title, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	public int leaveCountHour(String usrId);
	
	public int leaveCountHourReal(String usrId);
	
	public void insertLeaveApplication(LeaveApplication leaveApplication);
	
	public void insertLeaveApplicationM(LeaveApplication leaveApplication);
	
	public void insertLeaveApplicationJ(LeaveApplication leaveApplication);
	
	public void insertLeaveApplicationC(LeaveApplication leaveApplication);
	
	public LeaveApplication detailLeaveOne(Long id);
	
	public Overwork detailoverworkOne(Long id);
	
	public String requestUsrIdLeave(Long id);
	
	public String requestUsrIdOverwork(Long id);
	
	public void leaveConformM(@Param("signPath") String signPath, @Param("id") Long id);
	
	public void overworkConformM(@Param("signPath") String signPath, @Param("id") Long id);
	
	public String leaveRequestUsrIdSelect(Long id);
	
	public String leaveKindSelect(Long id);
	
	public int leaveDateSelect(Long id);
	
	public int leaveHourSelect(Long id);
	
	public int usrVacationHour(String usrId);
	
	public int usrVacationHourReal(String usrId);
	
	public void setVacationusr(@Param("usrId") String usrId, @Param("result") int result);
	
	public void setVacationusrReal(@Param("usrId") String usrId, @Param("result") int result);
	
	public void insertoverworkA(Overwork overwork);
	
	public void insertoverworkAM(Overwork overwork);
	
	public void insertoverworkAJ(Overwork overwork);
	
	public void insertoverworkAC(Overwork overwork);
	
	public List<UserData> getUsrAllDatafromeditUsrleave();
	
	public void roundrobinMoveToM(String roundNo);
	
	public String usrNameForInsNoticeMessage(String usrId);
	
	public String usrENGNameForInsNoticeMessage(String usrId);
	
	public String leaveKindDetpfromNoticeMessage(Long id);
	
	public String leaveRequestUsrfromNoticeMessage(Long id);
	
	public String overWorkRequestUsrfromNoticeMessage(Long id);
	
	public String overWorkDetpfromNoticeMessage(Long id);
	
	public int sumPriceFromRoundNumNoticeMessage(String roundNo);
	
	public String orderNoFullNameFromNoticeMessage(Long id);
	
	public Long orderIdFromNoticeMessageFromRound(String roundNo);
	
	public String completYNfromLeaveConfromCEO(Long id);
	
	public int isUpdatePossible(Long id);
	
	public String leaveGetStage(Long id);
	
	public int leaveGetRequestDate(Long id);
	
	public int leaveGetRequestHour(Long id);
	
	public String leaveGettitle(Long id);
	
	public void addUserVacation(@Param("usrId") String usrId, @Param("leaveDate") int leaveDate);
	
	public void vacationDeletedAndChange(Long id);
	
	
}
