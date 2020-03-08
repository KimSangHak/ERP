package com.yuhannci.erp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yuhannci.erp.mapper.DrawingInspectMapper;
import com.yuhannci.erp.model.DataTableResponse;
import com.yuhannci.erp.model.DrawingInspect;

import lombok.extern.slf4j.Slf4j;

// 가공 관련된 서비스

@Service
@Slf4j
public class DrawingInspectService {
	
	@Autowired DrawingInspectMapper drawingInspectMapper;
	

}
