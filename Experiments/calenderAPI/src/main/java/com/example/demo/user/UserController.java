package com.example.demo.user;
import java.util.List;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class UserController {
	
	@Autowired
	UserRepository repository;
	
	
	//TODO
	//@RequestMapping(method = RequestMethod.GET, path = "/users")
	public List<Users> getAllUsers(){
		List<Users> result = repository.findAll();
		return result;
	}

}
