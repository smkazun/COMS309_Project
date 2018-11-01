package com.example.demo.event;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.event.Events;

@Repository
public interface EventRepository extends CrudRepository<Events, Integer> {

<<<<<<< HEAD
	public Optional<Events> findBycalendarid(Integer calendarid);
=======
>>>>>>> 1819c245f479a8d6d752684c196fd15a8a617262

}
