package com.rabbitmq.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librabbitmq.librabbitmq.constants.RabbitmqConstants;
import com.librabbitmq.librabbitmq.dto.request.ClienteDto;
import com.rabbitmq.product.service.RabbitMQMessage;

@RestController
@RequestMapping("/cliente")
public class RabbitController {

	@Autowired RabbitMQMessage mqMessage;
	
	@PostMapping
	public ResponseEntity<String> save(@RequestBody ClienteDto cliente) {
		ClienteDto c = new ClienteDto();
		c.setName(cliente.getName());
		c.setMessage(cliente.getMessage());
		
		mqMessage.sendMessage(RabbitmqConstants.EXCHANGE_DIRECT_DEPOSITO, RabbitmqConstants.ROUNTING_KEY_QUEUE_A, c);
		return ResponseEntity.status(HttpStatus.CREATED).body("Sucesso");
	}
}
