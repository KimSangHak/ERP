package com.yuhannci.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.db.EquipmentJobOrderPart;

@Mapper
public interface EquipmentOrderPartMapper {
	public List<EquipmentJobOrderPart> selectParts(@Param("equipmentOrderId") Long equipmentId, @Param("orderNo") String orderNo);
}
