package com.yuhannci.erp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.OutsourcingRequestEntry;
import com.yuhannci.erp.model.PostprocessRequestDrawingInfo;
import com.yuhannci.erp.model.PostprocessRequestInfo;
import com.yuhannci.erp.model.PostprocessSkipListEntry;
import com.yuhannci.erp.model.db.JobDesignDrawing;

@Mapper
public interface PostprocessMapper {
	// 후처리 등록 리스트
	List<JobDesignDrawing> selectPostprocessList(@Param("start") Integer start, @Param("length")Integer length, @Param("designDrawingIdList")long[] designDrawingIdList
											, @Param("orderDateFrom") Date orderDateFrom
											, @Param("orderDateTo") Date orderDateTo
											, @Param("device") String device
											, @Param("orderNoBase") String orderNoBase
											, @Param("orderNoExtra") String orderNoExtra
											, @Param("outsourcingPartnerId") String outsourcingPartnerId
											, @Param("processSourcing") String processSourcing
												);
	Integer selectPostprocessListCount(@Param("start") Integer start, @Param("length")Integer length, @Param("designDrawingIdList")long[] designDrawingIdList
											, @Param("orderDateFrom") Date orderDateFrom
											, @Param("orderDateTo") Date orderDateTo
											, @Param("device") String device
											, @Param("orderNoBase") String orderNoBase
											, @Param("orderNoExtra") String orderNoExtra
											, @Param("outsourcingPartnerId") String outsourcingPartnerId
											, @Param("processSourcing") String processSourcing
												);
	
	void updatePostprocessSkip(@Param("designDrawingIdList")long[] designDrawingIdList, @Param("withFinish") Boolean withFinish);
	
	// 후처리 스킵
	Integer selectSkipListCount(@Param("start") Integer start, @Param("length")Integer length, @Param("designDrawingIdList")long[] designDrawingIdList
			, @Param("orderDateFrom") Date orderDateFrom
			, @Param("orderDateTo") Date orderDateTo
			, @Param("device") String device
			, @Param("orderNoBase") String orderNoBase
			, @Param("orderNoExtra") String orderNoExtra
			, @Param("outsourcingPartnerId") String outsourcingPartnerId
			, @Param("processSourcing") String processSourcing
			) ;
	List<PostprocessSkipListEntry> selectSkipList(@Param("start") Integer start, @Param("length")Integer length, @Param("designDrawingIdList")long[] designDrawingIdList
			, @Param("orderDateFrom") Date orderDateFrom
			, @Param("orderDateTo") Date orderDateTo
			, @Param("device") String device
			, @Param("orderNoBase") String orderNoBase
			, @Param("orderNoExtra") String orderNoExtra
			, @Param("outsourcingPartnerId") String outsourcingPartnerId
			, @Param("processSourcing") String processSourcing
			);
	
	// 후처리 발주
	Integer selectOrderListCount(@Param("start") Integer start, @Param("length")Integer length, @Param("designDrawingIdList")long[] designDrawingIdList
			, @Param("orderDateFrom") Date orderDateFrom
			, @Param("orderDateTo") Date orderDateTo
			, @Param("device") String device
			, @Param("orderNoBase") String orderNoBase
			, @Param("orderNoExtra") String orderNoExtra
			, @Param("outsourcingPartnerId") String outsourcingPartnerId
			, @Param("processSourcing") String processSourcing
			) ;
	List<PostprocessSkipListEntry> selectOrderList(@Param("start") Integer start, @Param("length")Integer length, @Param("designDrawingIdList")long[] designDrawingIdList
			, @Param("orderDateFrom") Date orderDateFrom
			, @Param("orderDateTo") Date orderDateTo
			, @Param("device") String device
			, @Param("orderNoBase") String orderNoBase
			, @Param("orderNoExtra") String orderNoExtra
			, @Param("outsourcingPartnerId") String outsourcingPartnerId
			, @Param("processSourcing") String processSourcing
			);	
	
	// 후처리 발주 취소 (원장 변경)
	void updateCancelOrderMaster( @Param("designDrawingIdList") long[] designDrawingIdList);
	void updateCancelOrder( @Param("designDrawingIdList") long[] designDrawingIdList, @Param("abortReason") String abortReason);
	
	PostprocessRequestInfo selectDetailRequestInfo(@Param("postprocessRequestId") String postprocessRequestId, @Param("partnerId") String partnerId);
	List<PostprocessRequestDrawingInfo> selectDetailRequestList(@Param("postprocessRequestId") String postprocessRequestId, @Param("partnerId") String partnerId);
}
