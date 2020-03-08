package com.yuhannci.erp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.db.RegisteredFile;

@Mapper
public interface RegisteredFileMapper {
	public RegisteredFile selectFile(@Param("fileNo") Long fileNo, @Param("fileHash") String fileHash);
	public void insertFile(RegisteredFile file);
}
