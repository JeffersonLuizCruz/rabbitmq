package com.rabbitmq.consumer.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.consumer.exception.ExceptionHandlerRabbitMQ;

@Configuration
public class RabbitMqConfig {

	@Bean
	RabbitListenerContainerFactory<DirectMessageListenerContainer> rabbitListenerContainerFactory(
			ConnectionFactory connectionFactory) {
		DirectRabbitListenerContainerFactory factory = new DirectRabbitListenerContainerFactory();

		factory.setConnectionFactory(connectionFactory);
		factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
		factory.setPrefetchCount(4);
		factory.setErrorHandler(new ExceptionHandlerRabbitMQ());
		return factory;
	}
}
