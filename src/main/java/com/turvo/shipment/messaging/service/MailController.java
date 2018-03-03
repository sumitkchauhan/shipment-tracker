package com.turvo.shipment.messaging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.turvo.shipment.messaging.model.Mail;

/**
 * Mail REST controller
 * @author schau32
 *
 */
@RestController
@RequestMapping("/mail")
public class MailController {
	@Autowired
	IMailingService mailingSvc;

	@RequestMapping(method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public String sendMail(@RequestBody Mail mail) {
		mailingSvc.sendMail(mail);
		return "mailed";
	}
}
