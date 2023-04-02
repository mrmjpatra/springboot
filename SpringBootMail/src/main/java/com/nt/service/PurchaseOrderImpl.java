package com.nt.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service("purchaseservice")
public class PurchaseOrderImpl implements IPurchaseOrder {
	
	@Autowired
	private JavaMailSender sender;
	@Value("${spring.mail.username}")
	private String fromEmail;

	@Override
	public String purchase(String[] items, double[] prices, String[] toEmails) throws Exception {
		//calculate the bill amt
		double billAmt=0.0;
		for(double p:prices) {
			billAmt+=p;
		}
		
		String msg=Arrays.toString(items)+" with prices"+Arrays.toString(prices)+" are purchased with Bill Amount "+billAmt;
		//send mail
		String status=sendMail(msg,toEmails);
		return msg+"------->"+status;
	}

	private String sendMail(String msg, String[] toEmails) throws MessagingException {
		MimeMessage message=sender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message,true);
		helper.setFrom(fromEmail);
		helper.setCc(toEmails);
		helper.setSubject("Open it to know");
		helper.setSentDate(new Date());
		helper.setText(msg);
		helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
		sender.send(message);
		return "mail sent";
		
	}

}
