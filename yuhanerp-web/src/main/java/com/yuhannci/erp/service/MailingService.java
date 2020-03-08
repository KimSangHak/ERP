package com.yuhannci.erp.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuhannci.erp.mapper.MailMapper;
import com.yuhannci.erp.model.EmailTransferEntry;
import com.yuhannci.erp.model.db.Mailing;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailingService {

	@Autowired MailMapper mailingMapper;

	@Transactional
	public List<EmailTransferEntry> getNextMailingList(){
		List<EmailTransferEntry> newEntry = new LinkedList<>();
		for(EmailTransferEntry entry : mailingMapper.selectUnsentMail()){
			entry.setTargetEMail( mailingMapper.selectMailingRecipients(entry.getId()));
			newEntry.add(entry);
		}
		List<Long> mailingId = newEntry.stream().map(a -> a.getId()).collect(Collectors.toList());
		
		if(mailingId.size() > 0)	// 2018-04-15 :: 대상이 있는 경우에만 R -> Q 로 변경 (쿼리 에러 나야는데 안남 -_-)
			mailingMapper.markSending(mailingId);
		
		return newEntry;
	}
	
	public void markSent(Long mailingId, boolean succeeded){
		mailingMapper.updateMailSendStatus(mailingId, succeeded ? "F" : "E");
	}
	
	public Integer resetSendingStatus() {
		return mailingMapper.resetSendingStatus();
	}
	
	@Transactional
	public void addNewMail(EmailTransferEntry body) throws Exception {
	
		if(body.getTargetUserIdList().isEmpty()){
			log.warn("수신자 목록이 없음");
			throw new Exception("수신자 목록이 없습니다");
		}
		
		mailingMapper.addNewMail(body);		
		mailingMapper.addMailRecipients(body.getId(), (String[]) body.getTargetUserIdList().toArray());
		log.info("등록된 메일 ID = " + body.getId());
	}
	@Transactional
	public void addNewExtgernalMail(EmailTransferEntry body) throws Exception {
	
		if(body.getTargetUserIdList().isEmpty()){
			log.warn("수신자 목록이 없음");
			throw new Exception("수신자 목록이 없습니다");
		}
		
		mailingMapper.addNewMail(body);		
		mailingMapper.addMailExternalRecipients(body.getId(), (String[]) body.getTargetUserIdList().toArray());
		log.info("등록된 메일 ID = " + body.getId());
	}	
}
