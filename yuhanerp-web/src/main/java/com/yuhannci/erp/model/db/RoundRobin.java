package com.yuhannci.erp.model.db;

import java.util.Date;

import lombok.Data;

@Data
public class RoundRobin {
	
	  Long id;
	  String roundNo;
	  Long jobDesignDrawingId;
	  Long jobOrderId;
	  String title;
	  String requestReason;
	  String roundKind;
	  String customerId;
	  Date regDate;
	  String requestDept;
	  String requestUsr;
	  Date requestDate;
	  String unitNo;
	  String seq;
	  String description;
	  String modelNo;
	  String maker;
	  String partnerId;
	  int quantity;
	  int spare;
	  int price;
	  String code;
	  String comment;
	  String remark;
	  Long designFileNo;
	  String stage;
	  String conformStage;
	  String orderRequestId;
	  Date orderRequestedWhen;
	  String orderRequestUserId;
	  String deleted;
	  String deleteReason;
	  String deleteUserId;
	  int warehousingQuantity;
	  String warehousingUser;
	  Date warehousingDate;
	  Date passDate;
	  String passUsr;
	  String receiverUsr;
	  String receiveDept;
	  int outQuantity;
	  String outUser;
	  String outReason;
	  String statementType;
	  int stockQuantity;
	  int stockUseQuantity;
	  String signR;
	  String signM;
	  String signP;
	  String signJ;
	  String signC;
	  Date mDate;
	  Date pDate;
	  Date jDate;
	  Date cDate;
	  
	  //컬럼값 이외
	  int count;
	  String jobOrderNo;
	  String deptName;
	  String usrName;
	  String position;
	  String customerName;
}
