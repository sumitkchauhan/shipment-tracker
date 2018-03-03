package com.turvo.shipment.messaging.service;

import static com.turvo.shipment.util.Utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.turvo.shipment.messaging.model.Mail;

/**
 * Core mailing service class
 * 
 * @author schau32
 *
 */
@Service
public class MailingServiceImpl implements IMailingService {

	private static final Logger LOG = LoggerFactory.getLogger(MailingServiceImpl.class);
	@Autowired
	public JavaMailSender emailSender;

	@Override
	public void sendMail(Mail mail) {
		blockEmptyArgument(mail);
		SimpleMailMessage message = createMessage(mail);
		emailSender.send(message);
		LOG.debug("Sent mail to :To[{}], CC [{}], BCC [{}]", message.getTo(), message.getCc(), message.getBcc());
	}

	private static SimpleMailMessage createMessage(Mail mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mail.getToRecipients().toArray(new String[mail.getToRecipients().size()]));
		message.setBcc(mail.getBccRecipients().toArray(new String[mail.getBccRecipients().size()]));
		message.setFrom(mail.getFrom());
		message.setSubject(mail.getSubject());
		message.setText(mail.getContent());
		return message;
	}

}
