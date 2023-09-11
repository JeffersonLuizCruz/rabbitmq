package com.rabbitmq.consumer.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.librabbitmq.librabbitmq.constants.RabbitmqConstants;
import com.librabbitmq.librabbitmq.dto.request.ClienteDto;

@Component
public class ConsumerQueueA {

	@RabbitListener(queues = RabbitmqConstants.QUEUE_A)
	private void consumer(ClienteDto dados) {
		System.out.println(dados.getName());
		System.out.println(dados.getMessage());
	}
}
