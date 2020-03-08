package com.yuhannci.erp.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;


@Data
public class JobPurchaseAssembleList {
	Long id;
	Long jobOrderId;
	String orderType; // "JOB" : 설비진행, "JIG" : 치공구 진행
	String orderNoBase;
	String orderNoExtra;
	String orderFullNo;			//전체 도면번호
	String drawingUserName;
	String unitNo;
	String description;
	String modelNo;
	String maker;
	String partnerId;
	String partnerName;
	int quantity;
	int spare;
	String code;
	String comment;
	String remark;
	String stage;
	String stageText;
	String orderUserName;		//발주자
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date issueDate;				//발주 날짜
	int orderQuantity;			//발주수량
	int stockUseQuantity;		//재고사용수량
	int wareHousingQuantity;	//입고수량

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date wareHousingDate;		//입고날짜

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date passDate;				//불출 날짜
	String receiverUserName;	//인수자
}
