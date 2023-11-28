package com.course.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.kafka.model.User;

@Service
public class KafkaJsonConsumer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStringConsumer.class);

	@KafkaListener(topics = {"kafka-json"}, groupId = "MyGroup")
	public void getJsonMessage(User user) {
		LOGGER.info(String.format("user received by these -> %s", user.toString()));
	}
}
