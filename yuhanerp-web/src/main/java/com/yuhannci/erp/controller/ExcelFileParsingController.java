package com.yuhannci.erp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yuhannci.erp.model.StandardResponse;
import com.yuhannci.erp.model.Excel.DesignDrawingLine;
import com.yuhannci.erp.model.Excel.PurchaseLine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/upload/excel")
public class ExcelFileParsingController {
	
	// 파일이 첨부된 필드 이름
	// <input type="file" name="A1" ... /> 
	final String fileAttachedFieldName = "A1"; 

	/*
	@RequestMapping(value="/test", method={RequestMethod.POST})
	public StandardResponse uploadExcelFile1(MultipartHttpServletRequest request){
		MultipartFile file = request.getFile(fileAttachedFieldName);
		if(file == null){	
			log.error("no attached file");
						
			return StandardResponse.createResponse("NO_FILE");
		}		
		log.info("attached file = " + file.getOriginalFilename());
		
		final boolean isXLSX = file.getOriginalFilename().toLowerCase().endsWith(".xlsx");
		if(isXLSX){
			try{
				List<List<String>>result = readEntireExcelFile(file.getInputStream());
				
				StandardResponse res = StandardResponse.createResponse("OK");
				res.setData(result);
				return res;
			}catch(Exception e){
				log.error("엑셀 파일 읽는 중 오류", e);
				return StandardResponse.createResponse("ERROR_READING");
			}			
		}else{
			log.warn("Reading XLS file is not implemented");
			return StandardResponse.createResponse("NOT_SUPPORTED");
		}		
	}
	*/
	
	final String[] designDrawingFields = { "NO.", "DESCRIPTION", "DIMENSIONS", "MAT'L", "HEAT TREAT", "Q'TY", "", "APPLIED FINISH", "Q.C", "비           고"};
	final String[] purchaseFields = { "NO.", "DESCRIPTION", "MODEL No./SIZE", "MAKER", "PROVIDER", "Q'TY", "", "CODE", "COMMENT", "REMARK" };

	
	enum ExcelTemplateNames{
		drawing,		// 도면 등록
		purchase		// 구매 리스트 등록
	}
	
	// 도면 출도 리스트가 들어있는 엑셀 파일을 읽어 양식에 맞는 데이터 내려주기
	@RequestMapping(value="/{excelTemplate}", method={RequestMethod.POST})
	@ResponseBody
	public StandardResponse processDesignDrawingExcelFile(@PathVariable("excelTemplate") ExcelTemplateNames excelTemplate, MultipartHttpServletRequest request){
		// 첨부 여부 확인
		MultipartFile file = request.getFile(fileAttachedFieldName);
		if(file == null){	
			log.error("no attached file");
						
			return StandardResponse.createResponse("ERROR", "첨부된 파일이 없습니다");
		}		
		
		log.debug("attached filename is [" + file.getOriginalFilename());
		// XLSX 확인
		final boolean isXLSX = file.getOriginalFilename().toLowerCase().endsWith(".xlsx");
		if(!isXLSX){
			return StandardResponse.createResponse("ERROR", "지원되는 파일 형식이 아닙니다");
		}
		
		HashMap<String, List<List<String>>> allSheetData = null;
		// 내용 일괄 읽기
		try{
			allSheetData = readEntireExcelFile(file.getInputStream());
		}catch(Exception e){
			log.error("엑셀 파일 읽는 중 오류 발생", e);
			return StandardResponse.createResponse("ERROR", "엑셀 내용을 읽는 중 오류 발생");
		}
		
		List allLine = null; 	
		try{
			if(excelTemplate == ExcelTemplateNames.drawing )
				allLine = extractDesignDrawingData(allSheetData);
			else if(excelTemplate == ExcelTemplateNames.purchase)
				allLine = extractPurchaseData(allSheetData);
			
		}catch(IllegalArgumentException e){			
			return StandardResponse.createResponse("ERROR", e.getMessage());
		}catch(Exception e){
			log.error("엑셀 파일 읽는 중 오류 발생", e);
			return StandardResponse.createResponse("ERROR", "엑셀 내용을 읽는 중 오류 발생");
		}
		
		StandardResponse res = StandardResponse.createResponse( (allLine != null && allLine.size() > 0) ? "OK" : "EMPTY");
		res.setData(allLine);
		log.debug("총 " + allLine.size() + " 개의 데이터 반환");
		
		return res;
	}
	
	private List<PurchaseLine> extractPurchaseData(HashMap<String, List<List<String>>> allSheetData) {
		
		List<PurchaseLine> allLine = new LinkedList<>();
		
		// 'ALL' 이라는 시트가 있으면 다른 시트는 읽지 않는다
		final boolean hasAllSheet =	allSheetData.containsKey("ALL");
		String[] targetSheets = hasAllSheet ? new String[] {"ALL"} : allSheetData.keySet().stream().toArray(String[]::new);
		log.info("읽을 시트명 = [" + String.join(", ",  targetSheets) + "]");
		
		// 유효한 시트 찾기
		for(String sheetName : targetSheets){
			
			log.debug("시트 [" + sheetName + "] 처리 시작");
			List<List<String>> sheet = allSheetData.get(sheetName);
			Iterator<List<String>> it = sheet.iterator();

			boolean foundHeaderLine = false;
			int lineNo = 0;
			int repeatedEmptyLine = 0;
			
			// 한줄씩 한줄씩 읽어보자
			while(it.hasNext()){
				
				List<String> line = it.next();
				lineNo++;
				
				if(foundHeaderLine){
					// 헤더 지났으면 데이터로 처리
					Iterator<String> col = line.iterator();
					PurchaseLine data = new PurchaseLine();
					if(col.hasNext())
						data.setUnitNo(col.next());
					if(col.hasNext())
						data.setDescription(col.next());
					if(col.hasNext())
						data.setModelNo(col.next());
					if(col.hasNext())
						data.setMaker(col.next());
					if(col.hasNext())
						data.setProvider(col.next());
					if(col.hasNext())
						
						data.setQuantity(col.next());
					
		
					if(col.hasNext())
						data.setSpareQuantity(col.next());
					if(col.hasNext())
						data.setCode(col.next());
					if(col.hasNext())
						data.setComment(col.next());
					if(col.hasNext())
						data.setRemark(col.next());
					
					if(data.isEmpty())
						continue;
					
					if( data.isInvalid()){
						throw new IllegalArgumentException("[" + sheetName + "] 시트 " + lineNo + "번 줄 Unit No 필드에 [" + data.getUnitNo() + "]가 지정되었습니다");
						//continue;
					}
					String qty = data.getQuantity();
					
					String qty2 = String.valueOf((int)Math.round(Double.parseDouble(qty)));
					
					data.setQuantity(qty2);
					
					if(!((data.getSpareQuantity()).isEmpty())) {
						String spare = data.getSpareQuantity();
						
						String spare2 = String.valueOf((int)Math.round(Double.parseDouble(spare)));
						
						data.setSpareQuantity(spare2);
					}
					
					allLine.add(data);
					
				}else{
					if(line.size() < purchaseFields.length){
						log.debug(lineNo + " 라인 통과 --- " + line);
						continue;
					}
										
					boolean isSame = true;
					Integer errorColumn = null;
					String errorColumnData = null, orgColumnData = null;
					for(int i=0;i<purchaseFields.length;i++){
						if(line.get(i) == null || !purchaseFields[i].equals(line.get(i).trim())){
							isSame = false;
							errorColumn = i;
							errorColumnData = line.get(i);
							orgColumnData = purchaseFields[i];
							break;
						}
					}
					
					if(isSame){
						log.debug(lineNo + " 헤더 발견 ");
						
						foundHeaderLine = true;
						// 다음 한줄 무시
						if(it.hasNext())
							it.next();
					}else{
						log.debug(lineNo + " 줄 " + errorColumn + " 열 필드 구성이 달라 통과 ---- " + line + ", [" + errorColumnData + "] vs [" + orgColumnData + "]" );
						continue;
					}					
				}	
			}	// 한줄씩...
			if(!foundHeaderLine)
				throw new IllegalArgumentException("[" + sheetName + "] 시트에서 헤더를 찾을 수 없습니다. 양식을 확인해 주세요");
			
		}	// 시트 단위
		
		return allLine;
	}
	
	private List<DesignDrawingLine> extractDesignDrawingData(HashMap<String, List<List<String>>> allSheetData) {
		
		List<DesignDrawingLine> allLine = new LinkedList<>();
		
		// 유효한 시트 찾기
		for(String sheetName : allSheetData.keySet()){
			
			log.debug("시트 [" + sheetName + "] 처리 시작");
			List<List<String>> sheet = allSheetData.get(sheetName);
			Iterator<List<String>> it = sheet.iterator();

			boolean foundHeaderLine = false;
			int lineNo = 0;
			int repeatedEmptyLine = 0;
			
			// 한줄씩 한줄씩 읽어보자
			while(it.hasNext()){
				
				List<String> line = it.next();
				lineNo++;
				
				if(foundHeaderLine){
					// 헤더 지났으면 데이터로 처리
					Iterator<String> col = line.iterator();
					DesignDrawingLine data = new DesignDrawingLine();
					if(col.hasNext())
						data.setDrawingNo( col.next() );
					if(col.hasNext())
						data.setDescription(col.next());
					if(col.hasNext())
						data.setDimensions(col.next());
					if(col.hasNext())
						data.setMaterial(col.next());
					if(col.hasNext())
						data.setHeatTreat(col.next());
					if(col.hasNext())
						data.setQuantity(col.next());
					if(col.hasNext())
						data.setSpare(col.next());
					if(col.hasNext())
						data.setAppliedFinish(col.next());
					if(col.hasNext())
						data.setQc(col.next());
					if(col.hasNext())
						data.setNote(col.next());
					
					if(!StringUtils.isEmpty(data.getDrawingNo()))					
						allLine.add(data);
					
				}else{
					if(line.size() < designDrawingFields.length){
						log.debug(lineNo + " 라인 통과 --- " + line);
						continue;
					}
										
					boolean isSame = true;
					Integer errorColumn = null;
					for(int i=0;i<designDrawingFields.length;i++){
						if(line.get(i) == null || !designDrawingFields[i].equals(line.get(i).trim())){
							isSame = false;
							errorColumn = i;
							break;
						}
					}
					
					if(isSame){
						log.debug(lineNo + " 헤더 발견 ");
						
						foundHeaderLine = true;
						// 다음 한줄 무시
						if(it.hasNext())
							it.next();
					}else{
						log.debug(lineNo + " 줄 " + errorColumn + " 열 필드 구성이 달라 통과 ---- " + line);
						continue;
					}					
				}	
			}	// 한줄씩...
		}	// 시트 단위
		
		return allLine;
	}
	
	/*
	 * Active Sheet 의 내용을 통으로 읽어서 반환
	 */
	HashMap<String, List<List<String>>> readEntireExcelFile(InputStream is){
		try {
			XSSFWorkbook book = new XSSFWorkbook(is);
			final int Sheets = book.getNumberOfSheets();
			final int activeSheetNo = book.getActiveSheetIndex();
	
			HashMap<String, List<List<String>>> all = new HashMap<>();
			
			for(int i=0;i<Sheets;i++){
				List<List<String>> result = new LinkedList<List<String>>(); 
				XSSFSheet sheet = book.getSheetAt(i);
				Iterator<Row> iterator = sheet.iterator();
				while(iterator.hasNext()){
					
					Row row = iterator.next();
					
					Iterator<Cell> cellIterator = row.iterator();
					List<String> line = new LinkedList<String>();
					while(cellIterator.hasNext()){
						
						Cell cell = cellIterator.next();
						
		                switch (cell.getCellType()) 
		                {
		                    case Cell.CELL_TYPE_NUMERIC:
		                        line.add(String.valueOf(cell.getNumericCellValue()));
		                        break;
		                    case Cell.CELL_TYPE_STRING:
		                    	line.add(cell.getStringCellValue());
		                        break;
		                    case Cell.CELL_TYPE_BOOLEAN:
		                        line.add(String.valueOf(cell.getBooleanCellValue()));
		                        break;
		                    default:
		                        line.add("");
		                        break;
		                }					
					}
					
					result.add(line);
				}	// end of while

				all.put(sheet.getSheetName(), result);
			}
			
			return all;			
			
		} catch (IOException e) {
			log.error("엑셀 파일 읽는 중 오류 ", e);
		} 
		return null;
	
	}
	
}
