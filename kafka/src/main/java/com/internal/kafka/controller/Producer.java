/**
 * 
 */
package com.internal.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internal.kafka.model.User;

/**
 * @author sajit Git Data
 *
 */


@RestController
@RequestMapping("PushMessage")
public class Producer {

	public static final String TOPIC = "kafka-user-topic";
	
		
	@Autowired
	KafkaTemplate<String, User> kafkaTemplateSU;
	
	@Autowired 
	KafkaTemplate<String, String> kafkaTemplateSS;
	
	/**
	 * @param userName
	 * @return
	 */
	@GetMapping("/createUser/{userName}")
	public String createUser(@PathVariable("userName") String userName)
	{
		System.out.println("Pushingto kafka");
		User user = new User("sajith", "userName", "Name");
		kafkaTemplateSS.send(TOPIC,userName);
		System.out.println("Pushingto kafka");
		return userName+" Pushed to Kafka";
		
	}
	
	/**
	 * @param user
	 * @return
	 */
	@PostMapping("/createUser")
	public String postUsertoKafka(@RequestBody User user)
	{
		System.out.println("User Details"+user.toString() );
		kafkaTemplateSU.send(TOPIC,user.getName(),user);
		return user.getName()+ " Pushed to Kafka";
	}
}
