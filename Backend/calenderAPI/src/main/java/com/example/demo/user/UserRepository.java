package com.example.demo.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.user.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer>{

		//public List<Users> findAll();
		
		//List<Users> findById(@Param("id") int id);
}
