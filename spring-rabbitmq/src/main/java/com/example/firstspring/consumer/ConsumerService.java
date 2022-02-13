package com.example.firstspring.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.firstspring.config.MessagingConfig;
import com.example.firstspring.dto.OrderStatus;

@Component
public class ConsumerService {
	
	@RabbitListener(queues = MessagingConfig.QUEUE)
	public void printQueueOrder (OrderStatus orderStatus) {
		
		System.out.println(orderStatus);
		
		
	}
	

}
