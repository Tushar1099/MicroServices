package com.course.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaStringConsumer {

private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStringConsumer.class);
	
	@KafkaListener(topics = "kafka-string",groupId = "MyGroup")
	public void getMessageString(String message) {
		LOGGER.info(String.format("message received by these -> %s", message));
	}
}
