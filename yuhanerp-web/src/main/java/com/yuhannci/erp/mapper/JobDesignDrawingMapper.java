package com.yuhannci.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.db.JobDesignDrawing;
import com.yuhannci.erp.model.JobDesignDrawingOutSource;

@Mapper
public interface JobDesignDrawingMapper {

	//도면출도 후 생관에서 가공 대기중인 도면 리스트 출력
	public List<JobDesignDrawing> selectDrawingMachine();
	
	public List<JobDesignDrawing> selectSpecificDrawings(@Param("IDs") long[] id);
	
	public List<JobDesignDrawingOutSource> selectSpecificDrawingOuts(@Param("IDs") long[] id);
	
	public JobDesignDrawing selectSingleDesignDrawing(@Param("designDrawingId") Long id);
	
	public List<JobDesignDrawing> selectJobDrawingRecord(@Param("orderId") Long id, @Param("classification") String classification, @Param("reason") String reason, @Param("outsourcingEstimateRequestId") String outsourcingEstimateRequestId, @Param("outsourcingRequestId") String outsourcingRequestId);
	public List<JobDesignDrawing> selectJobDesignDrawingArray(@Param("ids") Long[] id );
}
