package com.turvo.shipment;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.turvo.shipment.messaging.model.Mail;
import com.turvo.shipment.model.ChannelType;
import com.turvo.shipment.model.ShipmentMessage;

/**
 * JMSListener class. Will interact with Mailing service
 * 
 * @author schau32
 *
 */
@Component
public class Receiver {
	@Value("${url.mailSvc}")
	private String mailSvc;
	@Value("${mail.sender}")
	private String sender;
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Sends notification over REST to mailing service
	 * 
	 * @param notification
	 */
	@JmsListener(destination = "${notification.queue.name}")
	public void receiveMessage(ShipmentMessage notification) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Mail mail = new Mail();
		mail.setFrom(sender);
		// get mail addresses for shipper
		mail.setToRecipients(notification.getShipment().getShipper().getAddresses().stream()
				.filter(address -> address.getChannelType().equals(ChannelType.EMAIL))
				.map(address -> address.getAddressValue()).collect(Collectors.toList()));
		mail.setSubject("Shipment Status : " + notification.getShipment().getShipmentId());
		mail.setContent("Last seen location :" + notification.getLastSeenLocation().getName() + " at time : "
				+ notification.getLastSeenDate());
		HttpEntity<Mail> entity = new HttpEntity<>(mail, headers);
		restTemplate.exchange(mailSvc, HttpMethod.POST, entity, String.class);
	}

}