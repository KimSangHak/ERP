package com.yuhannci.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhannci.erp.mapper.BulletinBoardMapper;
import com.yuhannci.erp.model.AndroidData;
import com.yuhannci.erp.model.BulletinOrderEntry;
import com.yuhannci.erp.model.db.NoticeMessage;
import com.yuhannci.erp.model.NoticeMessageList;
import com.yuhannci.erp.model.popData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BulletinBoardService {
	
	@Autowired BulletinBoardMapper bulletinBoardMapper;
	
	final int defaultMaxNoticeMessages = 10; 
	final int orderListPageSize = 4;
	
	public List<NoticeMessage> getRecentNoticeMessage(Integer N){
		
		return bulletinBoardMapper.selectNoticeList(N != null ? N : defaultMaxNoticeMessages);
		
	}
	
	public List<AndroidData> getNoticeToAndroid(){
		
		return bulletinBoardMapper.getNoticeToAndroid();
	}
	
	public List<AndroidData> getNoticeToAndroid2(){
		
		return bulletinBoardMapper.getNoticeToAndroid2();
	}
	
	public List<AndroidData> getNoticeToAndroid3NULL(){
		
		return bulletinBoardMapper.getNoticeToAndroid3NULL();
	}
	
	public List<AndroidData> getNoticeToAndroid3(){
		
		return bulletinBoardMapper.getNoticeToAndroid3();
	}
	
	public List<AndroidData> getNoticeToAndroid3ToDirector(){
		
		return bulletinBoardMapper.getNoticeToAndroid3ToDirector();
	}
	
	public List<AndroidData> getNoticeToAndroid3ToCEO(){
		
		return bulletinBoardMapper.getNoticeToAndroid3ToCEO();
	}
	
	public List<AndroidData> getNotice3ToManager(String deptCode){
		
		return bulletinBoardMapper.getNotice3ToManager(deptCode);
	}
	
	public List<NoticeMessageList> getNoticeMessagePage(Integer psize, Integer page){
		
		return bulletinBoardMapper.selectNoticePageList(psize, page);
	}
	
	public int getOrderListTotalPageCount(){
		int N = bulletinBoardMapper.selectTotalOrderListCount();
		int totalPages = (int) Math.floor( (double) N / (double) orderListPageSize );
		log.debug("총 " + N + " 개 ==> " + totalPages + " 페이지");
		return totalPages;
	}
	public List<BulletinOrderEntry> getOrderListEntries(int pageNo){
		return bulletinBoardMapper.selectOrderList( (pageNo - 1) * orderListPageSize, orderListPageSize);		
	}
	
	public int getProgressListTotalPageCount(String orderType){
		int N = bulletinBoardMapper.selectTotalProgressListCount(orderType);
		log.debug("총 " + N + " 개");
		return N;
	}
	
	public int getNoticeListTotalPageCount(){
		int N = bulletinBoardMapper.selectTotalNoticeCount();
		log.debug("총 " + N + " 개");
		return N;
	}
	
	public popData getPopupAlarmData(String type) {
		
		return bulletinBoardMapper.getPopupAlarmData(type);
		
	}
	
	public List<String> getAndroidToken() {
		return bulletinBoardMapper.getAndroidToken();
		
	}
	
	public List<String> getAndroidTokenCEO() {
		return bulletinBoardMapper.getAndroidTokenCEO();
		
	}
	
	public List<String> getAndroidTokenFactory() {
		return bulletinBoardMapper.getAndroidTokenFactory();
		
	}
	
	public List<String> getAndroidTokenPQ() {
		return bulletinBoardMapper.getAndroidTokenPQ();
		
	}
	
	public List<String> getAndroidTokenDirector() {
		return bulletinBoardMapper.getAndroidTokenDirector();
		
	}
	
	public List<String> getAndroidTokenManager(String dept) {
		return bulletinBoardMapper.getAndroidTokenManager(dept);
		
	}
	
	public String getOneAndroidToken(String token) {
		int result; 
		
		result = bulletinBoardMapper.getOneAndroidToken(token);
		
		if(result==0) {
			return "Y";
		}
		else {
			return "N";
		}
	}
	
	public void insertNewAndroidToken(String token, String userId, String dept, String isManager, String isFactory, String isDelector, String isCEO, String isPQ) {
		bulletinBoardMapper.insertNewAndroidToken(token, userId, dept, isManager, isFactory, isDelector, isCEO, isPQ);
	}
	
	public List<AndroidData> getNoticeToAndroid4ToDirector(){
		return bulletinBoardMapper.getNoticeToAndroid4ToDirector();
	}
	
	public List<AndroidData> getNoticeToAndroid4ToCEO(){
		return bulletinBoardMapper.getNoticeToAndroid4ToCEO();
	}
	

	public List<AndroidData> getNoticeToAndroid4ToFactory(){
		return bulletinBoardMapper.getNoticeToAndroid4ToFactory();
	}
	
	public List<AndroidData> getNoticeToAndroid4ToManager(String deptCode){
		return bulletinBoardMapper.getNoticeToAndroid4ToManager(deptCode);
	}
}
