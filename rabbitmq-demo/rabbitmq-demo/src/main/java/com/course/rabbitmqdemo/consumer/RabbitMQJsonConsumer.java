package com.course.rabbitmqdemo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.course.rabbitmqdemo.dto.User;


@Service
public class RabbitMQJsonConsumer {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

	
	// RabbitListner annotation listen and consume from queue
    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(User user){
        LOGGER.info(String.format("Received JSON message -> %s", user.toString()));
    }
}    