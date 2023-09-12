package com.rabbitmq.product.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.librabbitmq.librabbitmq.constants.RabbitmqConstants;

import jakarta.annotation.PostConstruct;


@Component
public class RabbitmqConfig {
	
	@Autowired private AmqpAdmin amqpAdmin;
	
	private Queue queue(String queue) {
		return new Queue(queue, true, false, false);
	}
	
	private DirectExchange directExchange() {
		return new DirectExchange(RabbitmqConstants.EXCHANGE_DIRECT_DEPOSITO);
	}
	
	private Binding bindin(Queue queue, DirectExchange exchange) {
		return new Binding(queue.getName(), Binding.DestinationType.QUEUE, RabbitmqConstants.EXCHANGE_DIRECT_DEPOSITO, RabbitmqConstants.ROUNTING_KEY_QUEUE_A, null);
	}
	
	@PostConstruct
	private void init() {
		Queue queue = this.queue(RabbitmqConstants.QUEUE_A);
		
		DirectExchange directExchange = this.directExchange();
		
		Binding bindin = bindin(queue, directExchange);
		
		this.amqpAdmin.declareQueue(queue);
		this.amqpAdmin.declareExchange(directExchange);
		this.amqpAdmin.declareBinding(bindin);
		
	}
}
