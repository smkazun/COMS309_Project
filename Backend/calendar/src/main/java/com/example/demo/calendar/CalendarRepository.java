package com.example.demo.calendar;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.user.Users;

@Repository
public interface CalendarRepository extends CrudRepository<Calendar, Integer> {

	public Optional<Calendar> findByCalendarid(Integer calendarid);
	
}
