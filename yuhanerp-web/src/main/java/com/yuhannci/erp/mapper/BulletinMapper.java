package com.yuhannci.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yuhannci.erp.model.db.Bulletin;

@Mapper
public interface BulletinMapper {

	public List<Bulletin> selectRecentBulletin();
	
}
