package com.yuhannci.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.EmailTransferEntry;
import com.yuhannci.erp.model.db.Mailing;

@Mapper
public interface MailMapper {

	public List<EmailTransferEntry> selectUnsentMail(); 
	public List<String> selectMailingRecipients(@Param("mailingId") Long mailingId);
	public void markSending(@Param("mailingId") List<Long> mailingIds );
	
	public Integer resetSendingStatus();
	public void updateMailSendStatus(@Param("id") Long mailingId, @Param("send_status") String sendStatus);
	
	public void addNewMail(Mailing request);
	public void addMailRecipients(@Param("mailId") Long mailId, @Param("recipients") String[] recipients);	
	public void addMailExternalRecipients(@Param("mailId") Long mailId, @Param("recipients") String[] recipients);	
}
