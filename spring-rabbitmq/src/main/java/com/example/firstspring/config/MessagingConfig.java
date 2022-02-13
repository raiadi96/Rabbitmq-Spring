package com.example.firstspring.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

	public static final String QUEUE = "aditya-q";
	public static final String EXCHANGE = "aditya-xchange";
	public static final String ROUTINGKEY = "aditya-routingKey";
	
	@Bean
	public Queue queue() {
		return new Queue(QUEUE);
		}
	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(EXCHANGE);
		
	}
	
	@Bean
	public Binding binding(Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with(ROUTINGKEY);	
	}
	
	@Bean
	public MessageConverter convert() {
		
		return new Jackson2JsonMessageConverter();
		
	}
	
	@Bean
	public AmqpTemplate rabbitMqTemplate(ConnectionFactory connectionFactory){
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(convert());
		return rabbitTemplate;
	}
	
}
