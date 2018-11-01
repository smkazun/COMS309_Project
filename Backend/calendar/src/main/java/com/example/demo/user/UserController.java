package com.example.demo.user;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.user.Users;
import com.example.demo.calendar.CalendarRepository;
import com.example.demo.calendar.Calendar;
import com.example.demo.event.Events;
import com.example.demo.user.UserRepository;


@RestController
@RequestMapping(path = "/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CalendarRepository calendarRepository;
	
	/*
	@RequestMapping(method = RequestMethod.POST, path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestBody Users user) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Users n = new Users();
		n.setName(user.getName());
		n.setEmail(user.getEmail());
		n.setUserType(user.getUserType());
		userRepository.save(n);
		return "Saved";
	}
	
	*/
	
	
	//TODO This method can be in used in place of getAllUsers() or vice-versa
	@GetMapping(path="/test")
	public @ResponseBody Iterable<Users> getAllUsersTest() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
		
	}


	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	//creates a new user
	@RequestMapping(method = RequestMethod.POST, path = "/new")
	public @ResponseBody String saveUser(@RequestBody Users user) {
		userRepository.save(user);
		return "New User " + user.getname() + " saved";
	}
	
	//return all user accounts
	@RequestMapping(method = RequestMethod.GET, path = "/all")
	@ResponseBody
	public List<Users> getAllUsers(){
		logger.info("Entered into Controller Layer");
		List<Users> results = (List<Users>) userRepository.findAll(); //?
		logger.info("Number of records fetched:" + results.size());
		return results;
	}
	
	//finds a user based on their id
	@RequestMapping(method = RequestMethod.GET, path = "/{userId}")
	@ResponseBody
	public Optional<Users> findUserById(@PathVariable("userId") int id){
		logger.info("Entered into Controller Layer");
		Optional<Users> results = userRepository.findByuserid(id);
		return results;
	}
	
	//find user by name
	@RequestMapping(method = RequestMethod.GET, path = "/{userName}")
	@ResponseBody
	public Optional<Users> findUserByName(@PathVariable("userName") String name)
	{
		logger.info("entered in findUserByName method");
		//Optional<Users> results = userRepository. //TODO
		return null;
		
	}
	
	//removes a user
	@RequestMapping(method = RequestMethod.POST, path = "/remove") //TODO NOTE: This actually deletes by id
	@ResponseBody
	public String removeUser(@RequestBody Users user)
	{
		String deletedUsersName = user.getname();
		logger.info("deleted person: " + deletedUsersName);
		userRepository.delete(user);
		return deletedUsersName +" has been deleted";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/calendars/{Userid}")
	@ResponseBody
	Set<Map<String,Object>> getCalendarsForUser(@PathVariable Integer Userid)
	{
		Optional<Users> user = userRepository.findByuserid(Userid);
		
		Set<Map<String,Object>> calendars = new HashSet<Map<String,Object>>();
		if(user.isPresent())
		{
			for(Calendar c : user.get().getcalendars())
			{
				
				calendars.add(c.toDTO());
			}
		}
		return calendars;
	}
	
}
