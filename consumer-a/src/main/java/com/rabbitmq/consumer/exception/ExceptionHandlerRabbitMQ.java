package com.rabbitmq.consumer.exception;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.util.ErrorHandler;

public class ExceptionHandlerRabbitMQ implements ErrorHandler{

	@Override
	public void handleError(Throwable t) {
		
		String queueFail = ((ListenerExecutionFailedException)t).getFailedMessage().getMessageProperties().getConsumerQueue();
		System.out.println(queueFail);
		
		Message payloadMessageFail = ((ListenerExecutionFailedException)t).getFailedMessage();
		System.out.println(payloadMessageFail);
		
		Throwable causeFail = ((ListenerExecutionFailedException)t).getCause();
		System.out.println(causeFail);
		
		throw new AmqpRejectAndDontRequeueException("Não deve retornar a fila!");
	}

}
