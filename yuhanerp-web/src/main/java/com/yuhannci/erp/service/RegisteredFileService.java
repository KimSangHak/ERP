package com.yuhannci.erp.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yuhannci.erp.mapper.RegisteredFileMapper;
import com.yuhannci.erp.mapper.SystemConfigMapper;
import com.yuhannci.erp.model.db.RegisteredFile;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegisteredFileService {
	@Autowired RegisteredFileMapper registeredFileMapper;
	@Autowired SystemConfigMapper systemConfigMapper;
	
	public final String fileSavePathKey = "attached_file_save_path";
	
	public enum FileCategory{
		DRAWING("DRAWING"),
		IMG("IMG"),
		DOC("DOC"),
		PPT("PPT"),
		ETC("ETC");
		
		String val;
		FileCategory(String val){
			this.val = val;
		}
		
		public String getValue(){
			return val;
		}
	}
	
	Long registerFileDB(String originalFilename, Long fileSize, FileCategory fileCategory, String registeredSection, String userId){
		
		RegisteredFile file = new RegisteredFile();
		file.setUserId(userId);
		file.setRegisteredSection(registeredSection);
		file.setFileCategory(fileCategory.getValue() ); 
		file.setFileSize(fileSize);
		file.setOriginalFilename(originalFilename);
		
		registeredFileMapper.insertFile(file);
		
		return file.getId();
	}
	
	File getExpectedFilePath(Long fileNo, String fileCategory, String registeredSection){
		String path = systemConfigMapper.selectValue(fileSavePathKey).replace('\\', '/');
		String filename = registeredSection + "_" + fileCategory + "_" + fileNo;
		return Paths.get(path, filename).toFile();
	}
	
	// 파일을 파일 시스템에 저장 후에, DB 에 등재하고 발생한 파일 번호를 반환한다
	@Transactional
	public Long saveFile(MultipartFile file, FileCategory fileCategory, String registeredSection, String userId) throws Exception{
		if(file == null || file.isEmpty())
			return null;
		
		Long fileNo = null;
		fileNo = registerFileDB(file.getOriginalFilename(), file.getSize(), fileCategory, registeredSection, userId);
		File expectedFile = getExpectedFilePath(fileNo, fileCategory.toString(), registeredSection);
		
		log.info("첨부 파일 [" + file.getOriginalFilename() + "] 을 DB 에 등록함 FileNo = " + fileNo + ", 예상 파일 경로 = " + expectedFile);
		
		file.transferTo(expectedFile);		
		return fileNo;
	}
	
	public RegisteredFile queryFileInformation(String fileHash){
		RegisteredFile entry = registeredFileMapper.selectFile(null, fileHash);
		if(entry != null){
			File filePath = getExpectedFilePath(entry.getId(), entry.getFileCategory(), entry.getRegisteredSection() );
			entry.setStorageFilePath( filePath.toString()  ) ;
		}
		return entry;
	}
}
