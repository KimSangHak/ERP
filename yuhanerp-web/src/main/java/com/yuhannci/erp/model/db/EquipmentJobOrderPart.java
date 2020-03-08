package com.yuhannci.erp.model.db;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class EquipmentJobOrderPart {
	Long id;
	Long equipment_id;
	
	String deleted;
	String unitName;
	Date designCompleteDate;
	Date purchaseDate;
	Date processDate;
	Date testDate;
	Date assemblyDate;				
	Date programDate;					// 작업설비 진행 할 때만 사용
	
	final static SimpleDateFormat shortDateFormat = new SimpleDateFormat("yy.MM.dd");
	
	public String getPurchaseDateString(){
		return purchaseDate != null ? shortDateFormat.format(purchaseDate) : "-";
	}
	public String getDesignCompleteDateString(){
		return designCompleteDate != null ? shortDateFormat.format(designCompleteDate) : "-";
	}
	public String getProcessDateString(){
		return processDate != null ? shortDateFormat.format(processDate) : "-";
	}
	public String getTestDateString(){
		return testDate != null ? shortDateFormat.format(testDate) : "-";
	}
	public String getAssemblyDateString(){
		return assemblyDate != null ? shortDateFormat.format(assemblyDate) : "-";
	}
	public String getProgramDateString(){
		return programDate != null ? shortDateFormat.format(programDate) : "-";
	}
	
}
