package com.yuhannci.erp.model.db;

import java.util.Date;
import lombok.Data;

@Data
public class JobPurchaseDB {
	
	  Long id;
	  Long jobDesignDrawingId;
	  Long jobOrderId;
	  String customerId;
	  Date regDate;
	  String unitNo;
	  String seq;
	  String description;
	  String modelNo;
	  String maker;
	  String partnerId;
	  int quantity;
	  int spare;
	  String code;
	  String comment;
	  String remark;
	  Long designFileNo;
	  String stage;
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
	  int outQuantity;
	  String outUser;
	  String outReason;
	  String statementType;
	  int stockQuantity;
	  int stockUseQuantity;
	  Date robinRequestDate;
	  String roundRobinYN;

}
