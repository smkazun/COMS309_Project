package com.example.demo.user;

//import java.util.List;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.demo.user.Users;
@Repository
public interface UserRepository extends CrudRepository<Users, Integer>{

		//public List<Users> findAll();
		
		//List<Users> findById(@Param("id") int id);
}
