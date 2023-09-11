package com.rabbitmq.product.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQMessage {

	@Autowired private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(String exchange, String routingKey,Object message) {
		this.rabbitTemplate.convertAndSend(exchange, routingKey, message);
	}
}
