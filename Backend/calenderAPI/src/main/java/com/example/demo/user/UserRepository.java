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
	@Query(  value ="SELECT e.name\r\n" + 
			"FROM users e, access_calander b\r\n" + 
			"where e.id = b.client_id\r\n" + 
			"and b.calander_id = ?1;", nativeQuery = true)
	public List<Users> getAllNames(Integer id);

}
