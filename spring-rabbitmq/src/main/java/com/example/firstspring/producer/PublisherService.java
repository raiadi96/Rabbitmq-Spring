package com.example.firstspring.producer;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstspring.dto.Order;
import com.example.firstspring.dto.OrderStatus;
import com.example.firstspring.config.MessagingConfig;

@RestController
@RequestMapping("/order")
public class PublisherService {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@PostMapping("/{restaurantName}")
	public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
		order.setOrderId(UUID.randomUUID().toString());
		OrderStatus orderStatus = new OrderStatus(order, "PROCESSING", "Currently processing order");
		rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTINGKEY, orderStatus);
		return "Success";
	}
	
}
