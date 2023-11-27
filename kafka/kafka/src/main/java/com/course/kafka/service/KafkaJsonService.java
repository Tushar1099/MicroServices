package com.course.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.course.kafka.model.User;

@Service
public class KafkaJsonService {

	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaJsonService.class);
	
	public void sendUserJson(User user) {
		LOGGER.info(String.format("Messgae received -> %s", user.toString()));
		Message<User> message = MessageBuilder.withPayload(user)
									.setHeader(KafkaHeaders.TOPIC, "kafka-json")
									.build();
		
		kafkaTemplate.send(message);
	}
}
