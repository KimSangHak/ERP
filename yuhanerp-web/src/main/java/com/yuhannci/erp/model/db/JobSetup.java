package com.yuhannci.erp.model.db;

import java.util.Date;
import lombok.Data;

@Data
public class JobSetup {

	Long id;
	Long orderId;
	String workStatus;
	
	Date	expectBuyoffInDate;
	Date	expectBuyoffOutDate;
	Date	expectShippingDate;
	Date	expectFinishDate;
	Date	finishBuyoffInDate;
	Date	finishBuyoffOutDate;
	Date	shippingDate;
	Date	setupStartDate;
	Date	setupFinishDate;

	String note;
  
	String managerId;
	String assemble1Id;
	String assemble2Id;
	String assemble3Id;

	String pgm1Id;
	String pgm2Id;
	String pgm3Id;
	
	String wiring1Id;
	String wiring2Id;
	String wiring3Id;
	
	String robot1Id;
	String robot2Id;
	String robot3Id;
	
	String business1Id;
	String business2Id;
	String business3Id;
}
