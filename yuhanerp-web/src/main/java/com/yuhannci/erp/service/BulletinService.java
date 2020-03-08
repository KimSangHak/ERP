package com.yuhannci.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhannci.erp.mapper.BulletinMapper;
import com.yuhannci.erp.model.db.Bulletin;

@Service
public class BulletinService {

	@Autowired BulletinMapper bulletinMapper;
	
	public List<Bulletin> getRecentBulletin(){
		
		return bulletinMapper.selectRecentBulletin();
		
	}
	
}
