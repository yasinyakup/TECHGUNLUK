package com.yaytech.techgunluk.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.yaytech.techgunluk.exception.TechGunlukException;
import com.yaytech.techgunluk.model.NotificationEmail;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
	private final JavaMailSender javaMailSender;
	private final MailContentBuilder mailContentBuilder;
	

	void sendMail(NotificationEmail notificationEmail) {
		
			
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setTo(notificationEmail.getRecipient());
			mimeMessageHelper.setSubject(notificationEmail.getSubject());
			mimeMessageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
			System.out.println("in mail send");
			javaMailSender.send(mimeMessage);
			log.info("Activation mail sent!");
		} catch (MailException e) {
			e.printStackTrace();
//			throw new TechGunlukException("Exception occured when sending mail to "+notificationEmail.getRecipient());
			// TODO: handle exception
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	void sendMail(NotificationEmail notificationEmail) {
//		MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
//			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
//			
//			mimeMessageHelper.setTo(notificationEmail.getRecipient());
//			mimeMessageHelper.setSubject(notificationEmail.getSubject());
//			mimeMessageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
//		};
//		try {
//			javaMailSender.send(mimeMessagePreparator);
//			log.info("Activation mail sent!");
//		} catch (MailException e) {
//			throw new TechGunlukException("Exception occurred when sending mail to "+notificationEmail.getRecipient());
//			// TODO: handle exception
//		}
//	}

}
