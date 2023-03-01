package com.seamus.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	// initial static list of users
	private static List<User> users = new ArrayList<>();
	
	private static int usersCount = 0;
	
	static {
		users.add(new User(usersCount++, "Seamus",LocalDate.now().minusYears(26)));
		users.add(new User(usersCount++, "Hyessica",LocalDate.now().minusYears(25)));
		users.add(new User(usersCount++, "Jim",LocalDate.now().minusYears(35)));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		// Add a user id (from updated service count)
		user.setId(usersCount++);
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		// Predicate is a statement that determines if a value is true or false
		// For each member of a stream (user), check the following lamda expression:
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null); // use orElse(null) instead of get() here to prevent white page return (return nothing).
	}
	
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}
}

