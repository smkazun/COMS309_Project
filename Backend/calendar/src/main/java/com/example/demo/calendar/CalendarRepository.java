package com.example.demo.calendar;

import java.util.List;
import java.util.Optional;

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
public interface CalendarRepository extends CrudRepository<Calendar, Integer> {
	
	/**
	 * Finds the Calendar by using its unique id
	 * @param calendarid
	 * The unique id of the calendar
	 * @return
	 * Returns the specified calendar
	 */
	public Optional<Calendar> findByCalendarid(Integer calendarid);
	
}
