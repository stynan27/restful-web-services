package com.seamus.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	// initial static list of users
	private static List<User> users = new ArrayList<>();
	
	static {
		users.add(new User(1, "Seamus",LocalDate.now().minusYears(26)));
		users.add(new User(2, "Hyessica",LocalDate.now().minusYears(25)));
		users.add(new User(2, "Jim",LocalDate.now().minusYears(35)));
	}
}
