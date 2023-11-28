package com.course.kafka.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.kafka.service.KafkaJsonService;
import com.course.kafka.service.KafkaStringService;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

	@Autowired
	private KafkaStringService kafkaStringService;
	
	@Autowired
	private KafkaJsonService kafkaJsonService;
	
	@GetMapping
	public ResponseEntity<String> postMessageString(@RequestParam("message") String message){
		this.kafkaStringService.sendMessage(message);
		return ResponseEntity.ok("Messgae sent succesfully");
	}
	
	@PostMapping
	public ResponseEntity<String> postMessageJson(@RequestBody User user){
//		this.kafkaJsonService.sendUserJson(null);
		this.kafkaJsonService.sendUserJson(user);
		return ResponseEntity.ok("user sent succesfully");
	}
}
