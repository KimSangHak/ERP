package com.yuhannci.erp.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuhannci.erp.EmailConfig;
import com.yuhannci.erp.YerpConfig;
import com.yuhannci.erp.model.EmailTransferEntry;
import com.yuhannci.erp.service.MailingService;

import lombok.extern.slf4j.Slf4j;

// 메일 전송 담당하는 컨트롤러

@RestController
@Slf4j
public class MailingController {
	@Autowired EmailConfig emailConfig;
	@Autowired JavaMailSender javaMailSender;
	@Autowired MailingService mailingService;
	@Autowired YerpConfig yerpConfig;
	
	@RequestMapping("/mail/force_send")
	@Scheduled(cron="0 */1 * * * *")
	public Integer sendMails(){
		
		List<EmailTransferEntry> targets = mailingService.getNextMailingList();
		for(EmailTransferEntry mail : targets){
			boolean ok = performSendMail(mail);
			mailingService.markSent(mail.getId(), ok);
		}
		return targets.size();
	}
	
	@PostConstruct
	void resetSendingStatus() {
		
		if(!yerpConfig.getResetSendingEmail()){
			log.info("이메일 전송 상태 원복 안함");
			return;
		}
		
		Integer effected = mailingService.resetSendingStatus();
		log.info("전송 중이던 이메일 " + effected + " 건 상태 변경");
	}
	
	Long sendCounter = 0L;
	boolean performSendMail(EmailTransferEntry data){
		sendCounter++;
		String[] to = null;
		try{			
			if(data.getTargetEMail().isEmpty())
				throw new Exception("수신자 목록이 없음");
			
			// 수신 이멜 주소
			to = data.getTargetEMail().stream().toArray(String[]::new);
			
			// 헤더 추가된 본문
			String pre = "<!DOCTYPE html><html lang=\"ko\"><head><meta charset=\"utf-8\"><title>Smart Editor&#8482; WYSIWYG Mode</title><link rel=\"stylesheet\" type=\"text/css\" href=\"http://localhost:9996/css/style_pop.css\"><link rel=\"stylesheet\" type=\"text/css\" href=\"http://localhost:9996/css/multi.css\"></head><body style=\"height:0;-webkit-nbsp-mode:normal\">";
			String postfix = "</body></html>";
			String body = pre + data.getBody() + postfix;
			body = body.replace("/logo.png", "cid:logo");
			
			log.debug(sendCounter + " :: 메일 발송 준비 -- " + data);
			InternetAddress senderEmail = InternetAddress.parse(data.getSenderEmail())[0];			// 담당자
			InternetAddress mailerEmail = InternetAddress.parse(emailConfig.getSmtpFrom())[0];		// 발신 메일
			
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
			
			InternetAddress renewedSender = new InternetAddress(senderEmail.getAddress(), senderEmail.getPersonal(), helper.getEncoding());
			InternetAddress renewedMailer = new InternetAddress(mailerEmail.getAddress(), senderEmail.getPersonal(), helper.getEncoding());

			msg.addFrom(new InternetAddress[] { renewedMailer } );			
			msg.setSender(renewedSender );
			helper.setReplyTo(data.getSenderEmail());	// 회신 할 때는 메일 요청자에게 가도록
			helper.setSubject(data.getSubject());
			helper.setBcc(data.getSenderEmail()) ;		// 실제 요청한사람한테 숨은 참조로 보내주고
			helper.setTo( to );
			helper.setText(body, true);
			
			helper.addInline("logo", new ClassPathResource("static/logo.png"));
			
			// 아래 코드는 효과 없었음
			//msg.setHeader("Return-Path", MimeUtility.encodeText(data.getSenderEmail()) );
			
			javaMailSender.send(msg);
			
			log.info(sendCounter + " :: 수신자 = " + to + ", 요청자 = " + data.getSenderEmail() + ", 제목 = [" + data.getSubject() + "] ==> 메일 발송 완료");
			return true;
		}catch(Exception e){
			log.error(sendCounter + " :: 수신자 = " + to + ", 요청자 = " + data.getSenderEmail() + ", 제목 = [" + data.getSubject() + "] ==> 메일 발송 실패", e);
		}		
		return false;
	}
	
}
