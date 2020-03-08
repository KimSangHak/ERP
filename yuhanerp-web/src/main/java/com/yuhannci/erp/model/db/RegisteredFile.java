package com.yuhannci.erp.model.db;

import java.util.Date;

import lombok.Data;

@Data
public class RegisteredFile {
	Long id;
	
	Date whenRegistered;	
	Date whenDeleted;
	
	String userId;
	String registeredSection;
	String fileCategory;
	Long fileSize;
	
	String originalFilename;
	
	String hash;
	
	String storageFilePath;
}
