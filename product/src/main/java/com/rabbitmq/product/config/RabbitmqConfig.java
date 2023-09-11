package com.rabbitmq.product.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;


@Component
public class RabbitmqConfig {

	private static final String DIRECT_EXCHANGE			= "amq.direct";
	private static final String DIRECT_ROUNTING_KEY_A	= "rounting_key_direct_a";
	private static final String CONTABILIDADE 			= "CONTABILIDADE";
	
	@Autowired private AmqpAdmin amqpAdmin;
	
	private Queue queue(String queue) {
		return new Queue(queue, true, false, false);
	}
	
	private DirectExchange directExchange() {
		return new DirectExchange(DIRECT_EXCHANGE);
	}
	
	private Binding bindin(Queue queue, DirectExchange exchange) {
		return new Binding(queue.getName(), Binding.DestinationType.QUEUE, DIRECT_EXCHANGE, DIRECT_ROUNTING_KEY_A, null);
	}
	
	@PostConstruct
	private void init() {
		Queue queueContabilidade = this.queue(CONTABILIDADE);
		
		DirectExchange directExchange = this.directExchange();
		
		Binding bindin = bindin(queueContabilidade, directExchange);
		
		this.amqpAdmin.declareQueue(queueContabilidade);
		this.amqpAdmin.declareExchange(directExchange);
		this.amqpAdmin.declareBinding(bindin);
		
	}
}
