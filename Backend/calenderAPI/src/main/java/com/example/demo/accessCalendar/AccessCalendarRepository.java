package com.example.demo.accessCalendar;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.user.Users;

@Repository
public interface AccessCalendarRepository extends CrudRepository<AccessCalendar, Integer>{

	@Query(value ="INSERT INTO access_calander (client_id, calander_id)\r\n" + 
			"VALUES (?1,?2);", nativeQuery = true)
	public List<Users> AddUserId(Integer Userid, Integer CalendarId);
	
}
