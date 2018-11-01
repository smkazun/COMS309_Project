package com.example.demo.friends;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/Friends")
public class FriendsController {
	
	@Autowired
	private FriendsRepository friendsRepository;
	
	private final Logger logger = LoggerFactory.getLogger(FriendsController.class);
	
	//gets friendlist of a particular usser
	@GetMapping(path = "/friendslist")
	public List<Friends> getFriendsList()
	{
		logger.info("Entered FriendsController. Method: getFriendsList()");
		return null; //TODO
	}

}
