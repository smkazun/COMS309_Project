package com.example.demo.calendar;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.user.Users;

@Repository
public interface CalendarRepository extends CrudRepository<Calendar, Integer> {

	@Query(value ="select e.calendar_name " + 
			"from calendar e, access_calander c" + 
			"where e.calendar_id = c.calander_id " + 
			"and c.client_id = 1?")
	public List<Calendar> getCalendarNames(Integer userId);
	
}
