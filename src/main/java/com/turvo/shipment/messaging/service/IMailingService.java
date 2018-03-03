package com.turvo.shipment.messaging.service;

import com.turvo.shipment.messaging.model.Mail;

/**
 * Service class responsible for interacting with SMTP server and send out mail
 * 
 * @author schau32
 *
 */
public interface IMailingService {

	void sendMail(Mail mail);
}
