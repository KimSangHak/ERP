package com.yuhannci.erp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.InvalidParameterException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.yuhannci.erp.model.StandardResponse;
import com.yuhannci.erp.model.db.RegisteredFile;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.RegisteredFileService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FileLinkController {

	final String outputContentType = "application/octet-stream";
	
	@Autowired RegisteredFileService registeredFileService; 
	@Autowired LoginDongleService loginDongleService;

	void emitFile(String sourceFilePath, String attachedFilename, HttpServletResponse res) throws Exception{
		res.setContentType(outputContentType);
		res.setStatus(200);
		
		OutputStream os = null;
		os = res.getOutputStream();
		
		// TODO :: 파일 읽어서 밀어내기
		FileInputStream stream = null;
		stream = new FileInputStream(sourceFilePath);

		if(attachedFilename != null){
			// 첨부 파일 이름 설정하기
			res.addHeader("Content-Disposition", "attachment; filename='" +  attachedFilename + "'");
		}
		
		// 파일 내용 출력
		byte[] buffer = new byte[128 * 1024];
		int len;
		while( (len =stream.read(buffer)) != -1){
			os.write(buffer, 0, len);
		}
		os.flush();
		stream.close();
	}
	
	// 파일 업로드 처리	
	@RequestMapping(value="/upload/file", method={RequestMethod.POST})
	@ResponseBody
	public StandardResponse uploadFile(String section, RegisteredFileService.FileCategory fileCategory, MultipartHttpServletRequest request, String fileFieldName){
		
		StandardResponse res = null;
		
		try{
			if(section == null || fileCategory == null)
				throw new Exception("파일 위치 정보가 없습니다");
			
			if(fileFieldName == null)
				throw new InvalidParameterException("파일 첨부 필드이름이 지정되지 않았습니다");
			
			MultipartFile file = request.getFile(fileFieldName);
			if(file == null )
				throw new InvalidParameterException("첨부된 파일이 없습니다");
			
			System.out.println("저장전 저장전 저장전 저장전 저장전 저장전 저장전 저장전");
			// 파일 저장
			Long fileNo = registeredFileService.saveFile(file, fileCategory, section, loginDongleService.getLoginId());
			
			System.out.println("저장후 저장후 저장후 저장후 저장후 저장후 저장후 저장후");
			if(fileNo == null)
				throw new Exception("파일을 저장 중 오류");
			
			System.out.println("res전 res전 res전 res전 res전 res전 res전 res전");
			res = StandardResponse.createResponse("OK");
			System.out.println("res중 res중 res중 res중 res중 res중 res중 res중");
			res.setData(fileNo);
			System.out.println("res후 res후 res후 res후 res후 res후 res후 res후");
		}catch(InvalidParameterException e){			
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		}catch(Exception e){
			log.error("파일 업로드 처리 중 내부 오류 발생", e);
			res = StandardResponse.createResponse("ERROR", "내부 처리 오류");
		}
		return res;
	}
	
	@RequestMapping("/link/{fileHash}")
	public void requestImageFile(@PathVariable("fileHash") String fileHash, HttpServletResponse res, HttpServletRequest req){
				
		try{			
			if(fileHash == null)
				throw new Exception("파일 번호를 확인해 주세요");
			
			RegisteredFile registeredFile = registeredFileService.queryFileInformation(fileHash);
			if(registeredFile == null)
				throw new Exception("파일 정보를 찾을 수 없습니다");

			log.info("파일 [" + registeredFile.getId() + "번 ==> " + registeredFile.getOriginalFilename() + "] 을 다운로드. 클라이언트 = " + req.getRemoteHost());
			emitFile(registeredFile.getStorageFilePath(), registeredFile.getOriginalFilename(), res);
		}catch(FileNotFoundException e){			
			log.warn("파일 [" + fileHash + "] 을 요청 했으나 찾을 수 없음");
			res.reset();
			res.setStatus(404);
			e.printStackTrace();			
		}catch(Exception e){
			log.error("파일 [" + fileHash + "] 처리 중 오류", e);
			res.reset();
			
			// 클라이언트에게 보내는 응답
			res.setStatus(500);
			PrintWriter writer = null;
			try {
				writer = new PrintWriter(res.getOutputStream());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
			}
			writer.println("error while emitting image file. ");
			e.printStackTrace(writer);
		}
	}
	
	@RequestMapping("/popup/image_viewer/{hash}")
	public ModelAndView popupImageViewer(@PathVariable("hash") String hash){
		try{
			
			// TODO :: hash 유효성 체크 권한 체크 
			
			ModelAndView mv = new ModelAndView("util/popup_image_viewer");
			mv.addObject("hash", hash);
			return mv;
		}catch(Exception e){
			log.error("팝업 이미지 뷰어 :: 오류 ", e);
		}
		return null;
	}
}
