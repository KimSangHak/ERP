package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class JobSetupList
{
	Long id;
	String customerName;		//고객사 명
	String orderFullNo;			//Order No
	String device;				//설명
	String customerUser;		//고객사 담당 이름
	String businessUserId;		//영업담당
	String businessUserName;	//영업담당
	String designUserName;		//설계담당
	String workStatus;			//진행 상태-R:대기, F:완료, C:취소, I:사내작업중, O:고객사작업중, H:홀딩, S:중지
	String workStatusText;			//진행 상태-R:대기, F:완료, C:취소, I:사내작업중, O:고객사작업중, H:홀딩, S:중지
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date expectBuyoffInDate;		//예상 내부 Buyoff 날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date expectBuyoffOutDate;		//예상 고객사 Buyoff 날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date expectShippingDate;		//예상 출고날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date expectFinishDate;		//예상 셋업 완료날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date finishBuyoffInDate;		//내부 Buyoff 종료날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date finishBuyoffOutDate;		//고객사 Buyoff 종료날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date shippingDate;		//실제 출고일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date setupStartDate;		//setup 시작 날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date setupFinishDate;		//setup 완료 날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date outSetupStartDate;		//출장 시작날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date outSetupFinishDate;		//출장 종료날짜

	String note;				//비고
	String manager_id;
	String managerName;
	String assemble1Id;
	String assemble1Name;
	String assemble2Id;
	String assemble2Name;
	String assemble3Id;
	String assemble3Name;
	String pgm1Id;
	String pgm1Name;
	String pgm2Id;
	String pgm2Name;
	String pgm3Id;
	String pgm3Name;
	String wiring1Id;
	String wiring1Name;
	String wiring2Id;
	String wiring2Name;
	String wiring3Id;
	String wiring3Name;
	String robot1Id;
	String robot1Name;
	String robot2Id;
	String robot2Name;
	String robot3Id;
	String robot3Name;
	String business1Id;
	String business1Name;
	String business2Id;
	String business2Name;
	String business3Id;
	String business3Name;
}
