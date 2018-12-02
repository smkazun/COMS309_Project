package com.example.demo.calendar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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

/**
 * 
 * @author Sebastian Kazun
 *
 */
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
	
	/**
	 * makes a new calendar: names that calendar and adds the users
	 * @param calendar
	 * @param id
	 * @return
	 * Returns string confirming creation of calendar
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/new/{Userid}")
	public @ResponseBody String createNewCalendar(@RequestBody Calendar calendar, @PathVariable("Userid") int id) 
	{
		//calendarRepository.save(calendar);
		Users users = userRepository.findByuserid(id).get();
		Calendar c = calendar;
		
		users.getcalendars().add(c);
		c.getusers().add(users);
		
		calendarRepository.save(c);
		
		return "New Calendar " + calendar.getcalendarname() + " saved";
	}
	
	/**
	 * Adds users to an existing calendar
	 * @param cid
	 * @param uid
	 * @return
	 * Returns string confirming addition of users
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/{calendarid}/{Userid}")
	public @ResponseBody String addUsersToExistingCalendar(@PathVariable ("calendarid") int cid, @PathVariable("Userid") int uid) 
	{
		Users users = userRepository.findByuserid(uid).get();
		Calendar c = calendarRepository.findByCalendarid(cid).get();
		
		users.getcalendars().add(c);
		c.getusers().add(users);
		
		calendarRepository.save(c);
		
		return "users for  " + c.getcalendarname() + " saved";
	}
	
	
	/**
	 * gets a calendar by using its associated id
	 * @param calendarId
	 * @return
	 * Returns the calendar
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/{calendarId}") //TODO is this how we want to implement this?
	public Calendar getCalendarByCalendarId(@PathVariable("calendarId") int calendarId)
	{
		logger.info("Entered into CalendarController Layer");
		Calendar results = calendarRepository.findById(calendarId).get();

		return results;		
	}
	

	/**
	 * Gets all calendars
	 * @return
	 * Returns all calendars in database
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/all")
	@ResponseBody	
	public List<Calendar> getAllCalendars() 
	{
		logger.info("Entered into Controller layer");
		List<Calendar> results =  (List<Calendar>) calendarRepository.findAll();
		logger.info("number of records fetched: " + results.size());
		return results;
	}
	
	/**
	 * get all events to a calendar
	 * @param calendarid
	 * @return
	 * Returns a list of all the events for this calendar
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/events/{calendarid}")
	public List<Events> getAllEvents(@PathVariable Integer calendarid){
		List<Events> results = eventRepository.findBycalendarid(calendarid); 
		return results;
	}
		
	/**
	 * Get all users in a calendar
	 * @param calendarid
	 * @return
	 * Returns a set of all the users for this calendar
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/users/{calendarid}")
	@ResponseBody
	public Set <Users> getAllusers(@PathVariable Integer calendarid){
	
	Optional<Calendar> cal = calendarRepository.findByCalendarid(calendarid);
	
	Set<Users> a = calendarRepository.findByCalendarid(calendarid).get().getusers();
		
	List<Users> result = new ArrayList<Users>();
	
		if(cal.isPresent())
		{
			for(Users u : cal.get().getusers())
			{
				result.add(u);			}
		}
		return a;
	}
	
	/**
	 * Gets the most recently created calendar for a particular user
	 * @param userId
	 * @return
	 * Returns the calendarId of the most recently created calendar for a particular user
	 */
	@GetMapping(path = "/recent/{userId}")
	public @ResponseBody List<Calendar> returnMostRecentCalendarByUserId(@PathVariable Integer userId)
	{
		List <Calendar> c = (List<Calendar>) new Calendar();
		Optional<Users> user = userRepository.findById(userId);
		Set<Calendar> usersCalendars = user.get().getcalendars();
		List<Calendar> list = new ArrayList<Calendar>(usersCalendars);
		
		if(!usersCalendars.isEmpty())
		{
			c =  (List<Calendar>) list.get(list.size() - 1);
		}
		
		return c;
	}
	
	
}
