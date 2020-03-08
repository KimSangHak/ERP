package com.yuhannci.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.AndroidData;
import com.yuhannci.erp.model.BulletinOrderEntry;
import com.yuhannci.erp.model.db.JobOrder;
import com.yuhannci.erp.model.db.NoticeMessage;
import com.yuhannci.erp.model.NoticeMessageList;
import com.yuhannci.erp.model.popData;

@Mapper
public interface BulletinBoardMapper {
	List<NoticeMessage> selectNoticeList(@Param("N") int maxRows);
	int selectTotalOrderListCount();
	List<BulletinOrderEntry> selectOrderList(@Param("offset") int offset, @Param("N") int N);
	
	int selectTotalProgressListCount(@Param("orderType") String orderType);
	
	public List<AndroidData> getNoticeToAndroid();
	
	public List<AndroidData> getNoticeToAndroid2();
	
	public List<AndroidData> getNoticeToAndroid3();
	
	public List<AndroidData> getNoticeToAndroid3NULL();
	
	public List<AndroidData> getNoticeToAndroid3ToDirector();
	
	public List<AndroidData> getNoticeToAndroid3ToCEO();
	
	public List<AndroidData> getNotice3ToManager(String deptCode);
	
	int selectTotalNoticeCount();
	
	List<NoticeMessageList> selectNoticePageList(@Param("psize") Integer psize, @Param("page") Integer page);
	
	public popData getPopupAlarmData(String type);
	
	public List<String> getAndroidToken();
	
	public List<String> getAndroidTokenCEO();
	
	public List<String> getAndroidTokenFactory();
	
	public List<String> getAndroidTokenDirector();
	
	public List<String> getAndroidTokenManager(String dept);
	
	public List<String> getAndroidTokenPQ();
	
	public int getOneAndroidToken(String token);
	
	public void insertNewAndroidToken(@Param("token") String token, @Param("userId") String userId,
									  @Param("dept") String dept, @Param("isManager") String isManager,
									  @Param("isFactory") String isFactory, @Param("isDelector") String isDelector,
									  @Param("isCEO") String isCEO, @Param("isPQ") String isPQ);
	
	
	public List<AndroidData> getNoticeToAndroid4ToDirector();
	
	public List<AndroidData> getNoticeToAndroid4ToCEO();
	
	public List<AndroidData> getNoticeToAndroid4ToFactory();
	
	public List<AndroidData> getNoticeToAndroid4ToManager(String deptCode);
}
