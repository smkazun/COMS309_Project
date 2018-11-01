package com.example.demo.event;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.event.Events;

@Repository
public interface EventRepository extends CrudRepository<Events, Integer> {

	public Optional<Events> findBycalendarid(Integer calendarid);

}
