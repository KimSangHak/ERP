package com.yuhannci.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.db.Message;

@Mapper
public interface MessageMapper {

	public List<Message> selectRecentMessages(@Param("userId") String userId, @Param("N") Integer N);
	// 이용자에게 부여된 권한을 불러온다
	public Integer selectUnreadMessageCount(@Param("userId") String userId);	
}
