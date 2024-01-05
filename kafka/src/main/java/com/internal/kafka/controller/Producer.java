/**
 * 
 */
package com.internal.kafka.controller;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	public static final String TOPIC = "myTopicSteve";
	
		
	@Autowired
	KafkaTemplate<String, User> kafkaTemplateSU;
	
	@Autowired 
	KafkaTemplate<String, String> kafkaTemplateSS;

	Logger logger = LoggerFactory.getLogger(Producer.class.getName());

	/**
	 * @param userName
	 * @return
	 */
	@GetMapping("/createUser/{userName}")
	public String createUser(@PathVariable("userName") String userName)
	{
		User user = new User("Steve", "userName", "Name");
		kafkaTemplateSS.send(TOPIC,userName);
		logger.info("Pushing data to Kafka");
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
		
		//Types of Sends
//		Fire and Forget
		kafkaTemplateSU.send(TOPIC,user.getName(),user);
//		Synchronous send
		try {
			kafkaTemplateSU.send(TOPIC,user.getName(),user).get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Asynchronous Send
		return user.getName()+ " Pushed to Kafka";
	}
}
