package com.example.demo.calendar;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.user.Users;

@Repository
public interface CalendarRepository extends CrudRepository<Calendar, Integer> {

	@Query(value ="SELECT e.calendar_name, e.calendar_id \r\n" + 
			"from calendar e, access_calander c\r\n" + 
			"where e.calendar_id = c.calander_id\r\n" + 
			"and c.client_id =\r\n" + 
			"( \r\n" + 
			"select id\r\n" + 
			"from users b\r\n" + 
			"where b.name = 1?);", nativeQuery = true)
	public List<Calendar> getCalendarNames(String name);
	
}
