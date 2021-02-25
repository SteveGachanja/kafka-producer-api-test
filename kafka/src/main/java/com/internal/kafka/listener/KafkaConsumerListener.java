package com.internal.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.internal.kafka.model.User;

@Service
public class KafkaConsumerListener {
	
	public static final String TOPIC = "kafka-user-topic";
	
	@KafkaListener(topics = TOPIC, groupId = "Consumer_Group")
	public void Consume(String Name)
	{
		System.out.println("Message Pickeed ::"+Name);
	}

	@KafkaListener(topics = TOPIC, groupId = "Consumer_Group")
	public void ConsumeJson(User user)
	{
		System.out.println("Message Picked ::"+user.toString());
	}
	
}
