package com.course.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaStringService {

	private KafkaTemplate<String, String> kafkaTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStringService.class);

	public KafkaStringService(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(String message) {
		LOGGER.info(String.format("Messgae received -> %s", message));
		this.kafkaTemplate.send("kafka-string", message);
	}
}
