package com.yuhannci.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.OrderTypeEnum;
import com.yuhannci.erp.model.db.JobDesignDrawing;

@Mapper
public interface DesignDrawingMapper {

	/**
	 * 출도리스트 -> 도면 등록 팝업 -> 도면 등록 시 DB에 해당 자료 있는지 확인
	 * 
	 * @param orderNoBase
	 * @param orderNoExtra
	 * @param drawingNo
	 * @return Integer
	 */
	public Integer countDrawing(@Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra, @Param("drawingNo") String drawingNo);
	
	/**
	 * 출도리스트 -> 도면 등록 팝업 -> 도면 등록 시 호출로 해당 도면을 등록 
	 * @param jobDesignDrawing
	 */
	public void insertDrawing(JobDesignDrawing jobDesignDrawing);
	
	public String outScourcingPTNamefromHistory(String id);
}