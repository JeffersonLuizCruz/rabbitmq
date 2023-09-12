package com.rabbitmq.consumer.rabbitmq.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.librabbitmq.librabbitmq.constants.RabbitmqConstants;
import com.librabbitmq.librabbitmq.dto.request.ClienteDto;

@Component
public class ConsumerQueueA {

	@RabbitListener(queues = RabbitmqConstants.QUEUE_A, containerFactory = "rabbitListenerContainerFactory")
	private void consumer(ClienteDto dados, Message message) {
		boolean isRountingKey = RabbitmqConstants.ROUNTING_KEY_QUEUE_A.equalsIgnoreCase(message.getMessageProperties().getReceivedRoutingKey());
		if(isRountingKey) {
			System.out.println(dados.getMessage());
			System.out.println(dados.getName());
		} else {
			// Todo - Cenário para criar uma exception de erro de rota
			System.out.println("Ignorando mensagem com routing key: " + RabbitmqConstants.ROUNTING_KEY_QUEUE_A);
		throw new IllegalArgumentException("Argumento Ilegal!");
		}
	}
}
