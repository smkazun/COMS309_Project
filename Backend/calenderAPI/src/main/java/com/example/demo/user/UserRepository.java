package com.example.demo.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.user.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer>{

		//public List<Users> findAll();
		
		//List<Users> findById(@Param("id") int id);
	@Query(value ="SELECT e.name " + 
			 "FROM users e, access_calander b " +
			 "where e.id = b.client_id " + 
			 "and b.calander id = ?1")
	public List<Users> getAllNames(Integer id);

}
