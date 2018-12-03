package com.example.demo.user;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.List;

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
import com.example.demo.user.UserRepository;

/**
 * 
 * @author Sebastian Kazun
 *
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {
	
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CalendarRepository calendarRepository;
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	/**
	 * Creates a new user
	 * @param user
	 * The user to be saved
	 * @return
	 * Returns a string indicating the user saved
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/new")
	public @ResponseBody String saveUser(@RequestBody Users user) {
		userRepository.save(user);
		return "New User " + user.getname() + " saved";
	}
	
	/**
	 * Returns all user accounts
	 * @return
	 * Returns all user accounts
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/all")
	@ResponseBody
	public List<Users> getAllUsers(){
		logger.info("Entered into Controller Layer");
		List<Users> results = (List<Users>) userRepository.findAll(); //?
		logger.info("Number of records fetched:" + results.size());
		return results;
	}
	
	/**
	 * Finds a user based on their id
	 * @param id
	 * The unique id of the user
	 * @return
	 * Returns the user
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/{userId}")
	@ResponseBody
	public Optional<Users> findUserById(@PathVariable("userId") int id){
		logger.info("Entered into Controller Layer");
		Optional<Users> results = userRepository.findByuserid(id);
		return results;
	}
	
	
	/**
	 * Removes a user
	 * @param user
	 * The user to be deleted
	 * @return
	 * Returns string indicating the user deleted
	 */
	@RequestMapping(method = RequestMethod.DELETE, path = "/remove") 
	@ResponseBody
	public String removeUser(@RequestBody Users user) 
	{
		String deletedUsersName = user.getname();
		logger.info("deleted person: " + deletedUsersName);
		userRepository.delete(user);
		return deletedUsersName +" has been deleted";
	}
	
	/**
	 * Gets all calendars that are associated with a particular user
	 * @param Userid
	 * The unique id of the user
	 * @return
	 * Returns the calendars
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/calendars/{Userid}")
	@ResponseBody
	Set<Map<String,Object>> getCalendarsForUser(@PathVariable Integer Userid)
	{
		Optional<Users> user = userRepository.findByuserid(Userid);
		
		Set<Map<String,Object>> users = new HashSet<Map<String,Object>>();
		if(user.isPresent())
		{
			for(Calendar c : user.get().getcalendars())
			{
				users.add(c.toDTO(Userid));
			}
		}
		return users;
	}
	
	/**
	 * Returns the userType
	 * @param id
	 * The unique id of the user
	 * @return
	 * Returns the type of the user. i.e. Admin
	 */
	@GetMapping(path = "/userType/{id}")
	@ResponseBody
	public String getUserTypeById(@PathVariable("id") int id)
	{
		logger.info("entered usertype");
		Optional<Users> user = userRepository.findById(id);
		String userType = user.get().getusertype();
		
		return userType;
	}
		
	
	
	//methods for FriendsList
	/*
	//returns all friends of a particular user
	@GetMapping(path = "/friendslist")
	public Optional<Friends> getFriendsList(@PathVariable int id)
	{
		logger.info("Entered FriendsController. Method: getFriendsList()");
		
		Optional<Friends> list = friendsRepository.findById(id);
		return list;
	}
	
	//adds new friend to friendlist
	@PostMapping(path = "/add")
	public @ResponseBody String addFriend(@RequestBody Friends friend)
	{
		friendsRepository.save(friend);
		return "New Friend has been added";
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/remove") //TODO NOTE: This actually deletes by id
	@ResponseBody
	public String removeFriend(@RequestBody Friends user) 
	{
		logger.info("delete friend method");
		friendsRepository.delete(user);
		return " has been deleted";
	}
	
	*/
	
	
}
