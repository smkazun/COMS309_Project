package com.example.demo.calendar;

import java.util.List;
import java.util.Optional;

import javax.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.calendar.CalendarRepository;
import com.example.demo.user.Users;
import com.example.demo.calendar.Calendar;
import com.example.demo.user.*;


@RestController
@RequestMapping(path = "/calendar")
public class CalendarController {
	
	@Autowired
	private CalendarRepository calendarRepository;
	
	private final Logger logger = LoggerFactory.getLogger(CalendarController.class);
	
	//make new calendar: names that calendar and adds the users
	@RequestMapping(method = RequestMethod.POST, path = "/new")
	public @ResponseBody String createNewCalendar(@RequestBody Calendar calendar) 
	{
		calendarRepository.save(calendar);
		
		return "New Calendar " + calendar.getcalendarname() + " saved";
	}
	
	//gets a calendar by using its associated id
	@RequestMapping(method = RequestMethod.GET, path = "/{calendarId}") //TODO is this how we want to implement this?
	public @ResponseBody String getCalendarByCalendarId(@PathVariable("calendarId") int calendarId)
	{
		logger.info("Entered into CalendarController Layer");
		Optional<Calendar> results = calendarRepository.findById(calendarId);

		return results.toString();		
	}
	
	//gets all calendars
	@RequestMapping(method = RequestMethod.GET, path = "/all")
	@ResponseBody	
		public List<Calendar> getAllcalendars() {
		logger.info("Entered into Controller layer");
		List<Calendar> results =  (List<Calendar>) calendarRepository.findAll();
		logger.info("number of records fetched: " + results.size());
		return results;
	}
		
	
}
