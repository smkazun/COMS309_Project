package com.example.demo.user;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.user.Users;

/**
 * 
 * @author Sebastian Kazun
 *
 */
@Repository
public interface UserRepository extends CrudRepository<Users, Integer>{
	
	
	/**
	 * Finds a user by their unique id
	 * @param Userid
	 * The unique id of the user
	 * @return
	 * returns the user
	 */
	public Optional<Users> findByuserid(Integer Userid);
	
	
}
