package com.yuhannci.erp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SystemConfigMapper {

	public String selectValue(@Param("key") String key);
}
