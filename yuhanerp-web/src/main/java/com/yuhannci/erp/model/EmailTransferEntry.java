package com.yuhannci.erp.model;

import java.util.List;

import com.yuhannci.erp.model.db.Mailing;

import lombok.Data;

@Data
public class EmailTransferEntry extends Mailing{

	List<String> targetEMail;		// 전송할 이멜 주소 목록 (실제 메일 발송시 사용)
	List<String> targetUserIdList;	// 메일 등록할 때만 사용
}
