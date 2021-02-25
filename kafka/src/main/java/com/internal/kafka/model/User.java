package com.internal.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
public class User {
	
	
	@Getter @Setter String userID;
	@Getter @Setter String userName;
	@Getter @Setter String Name;
}
