package com.yuhannci.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.QcViewData;
import com.yuhannci.erp.model.QcFinishListData;

@Mapper
public interface QcViewDataMapper {

	public List<QcViewData> selectList();
	
	public QcFinishListData selectCheck(@Param("id") Long id);
	public List<QcFinishListData> selectFinishList(@Param("start") Integer start, @Param("length") Integer length,
			@Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra, 
			@Param("drawingNo") String drawingNo, @Param("mctDateFrom") String mctDateFrom, @Param("mctDateTo") String mctDateTo);
	
	int qcPlayStart(@Param("id") Long id);
	
	int qcDrawingStatusSet(@Param("dwid") Long dwid, @Param("atype") String atype);
	
	int qcHoldSet(@Param("id") Long id, @Param("atype") String atype, @Param("reason") String reason);
	
	int qcCompleteSet(@Param("id") Long id, @Param("resultok") String resultok, @Param("okcnt") int okcnt,  @Param("failcnt") int failcnt);
	
	int qcDeliverySet(@Param("id") Long id, @Param("fromid") String fromid, @Param("toid") String toid);
	
	int qcCompleteDrawingSet(@Param("dwid") Long dwid, @Param("okcnt") int okcnt,  @Param("failcnt") int failcnt);
	
	int qcDeliveryDrawingSet(@Param("dwid") Long dwid);
	
	public Long qcDeliveryGetOrderId(Long id);
	
	public int qcDeliveryGetQtywithSpare(Long id);
}
