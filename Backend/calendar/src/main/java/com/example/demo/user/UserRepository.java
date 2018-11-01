package com.example.demo.user;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.user.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer>{

		//public List<Users> findAll();
		
		//List<Users> findById(@Param("id") int id);
	@Query(  value ="SELECT e \r\n" + 
			"FROM users e, access_calander b\r\n" + 
			"where e.id = b.client_id\r\n" + 
			"and b.calander_id = ?1;", nativeQuery = true)
	public List<Users> getAllNames(Integer id);

	@Query( value = "select * from users;", nativeQuery = true)
	public List<Users> getAllUsers();
	
	//finds user
	public Optional<Users> findByuserid(Integer Userid);
	
}
