package com.rabbitmq.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.product.model.Cliente;
import com.rabbitmq.product.service.RabbitMQMessage;

@RestController
@RequestMapping("/cliente")
public class RabbitController {

	@Autowired RabbitMQMessage mqMessage;
	
	@PostMapping
	public ResponseEntity<String> save(@RequestBody Cliente cliente) {
		Cliente c = new Cliente();
		c.setName(cliente.getName());
		c.setMessage(cliente.getMessage());
		
		mqMessage.sendMessage("amq.direct", "rounting_key_direct_a", c);
		return ResponseEntity.status(HttpStatus.CREATED).body("Sucesso");
	}
}
