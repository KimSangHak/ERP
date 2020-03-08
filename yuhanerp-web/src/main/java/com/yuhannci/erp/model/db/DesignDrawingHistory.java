package com.yuhannci.erp.model.db;

import java.util.Date;
import lombok.Data;


@Data
public class DesignDrawingHistory {
	
	Long id;
	Long drawingId;
	String kind;
	Date regDate;
	String regUsr;
	String description;
	
	//컬럼이외의 값
	String usrName;
	String designDrawingNo;
	

}
