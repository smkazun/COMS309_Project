package com.example.demo.calendar;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
import com.example.demo.event.EventRepository;
import com.example.demo.event.Events;
import com.example.demo.calendar.Calendar;
import com.example.demo.user.*;


@RestController
@RequestMapping(path = "/calendar")
public class CalendarController {
	
	@Autowired
	private CalendarRepository calendarRepository;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private UserRepository userRepository;
	
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
	public Calendar getCalendarByCalendarId(@PathVariable("calendarId") int calendarId)
	{
		logger.info("Entered into CalendarController Layer");
		Calendar results = calendarRepository.findById(calendarId).get();

		return results;		
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
	
	//get all events to a calendar
	@RequestMapping(method = RequestMethod.GET, path = "/events/{calendarid}")
	public List <Events> getAllEvents(@PathVariable Integer calendarid){
		List<Events> results = eventRepository.findBycalendarid(calendarid);
		return results;
	}
		
	//get all users in a calendar
	@RequestMapping(method = RequestMethod.GET, path = "/users/{calendarid}")
	public List <Users> getAllusers(@PathVariable Integer calendarid){
	
	Optional<Calendar> cal = calendarRepository.findBycalendarid(calendarid);
		
	List<Users> result = null;
	
		if(cal.isPresent())
		{
			for(Users u : cal.get().getusers())
			{
				result.add(u);			}
		}
		return result;
	}
	
}
