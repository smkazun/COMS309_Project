package com.example.demo.event;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.event.Events;

/**
 * 
 * @author Sebastian Kazun
 *
 */
@Repository
public interface EventRepository extends CrudRepository<Events, Integer> {
	
	/**
	 * 
	 * @param calendarid
	 * @return 
	 * Returns the calendar associated with the given calendar id
	 */
	public List<Events> findBycalendarid(Integer calendarid);

}
