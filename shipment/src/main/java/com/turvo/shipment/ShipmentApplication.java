package com.turvo.shipment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class ShipmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShipmentApplication.class, args);
	}
}
