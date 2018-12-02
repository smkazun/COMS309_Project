package com.example.demo.event;

import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.event.Events;
import com.example.demo.user.Users;
import com.example.demo.calendar.Calendar;
import com.example.demo.calendar.CalendarRepository;
import com.example.demo.event.EventRepository;


/**
 * 
 * @author Sebastian Kazun
 *
 */
@RestController
@RequestMapping(path = "/event")
public class EventController {
	
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private CalendarRepository calendarRepository;
	
	private final Logger logger = LoggerFactory.getLogger(EventController.class);
	
	
	/**
	 * Saves a new event
	 * @param event
	 * @param id
	 * @return
	 * Returns a string indicating that a new event has been saved to the repository
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/new/{Calendarid}")
	public @ResponseBody String saveEvent(@RequestBody Events event, @PathVariable("Calendarid") int id) 
	{
		Calendar c = calendarRepository.findByCalendarid(id).get();
		Events e = event;
		eventRepository.save(e);
		
		return "New event saved";
	}
	
	
	/**
	 * Gets all the info about one event
	 * @param event
	 * @return
	 * Returns the calendarId, eventId, and event time as a string
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/eventInfo")
	@ResponseBody
	public String getAllEventInfo(@RequestBody Events event)
	{	
		return "calendarId: " + event.getcalendarid() + "\neventId: " + event.geteventid() + "\ntime: " + event.gettime(); //TODO add date
	}
	
	/**
	 * Removes an event
	 * @param event
	 * @return
	 * Returns a string indicating that the event has been deleted
	 */
	@RequestMapping(method = RequestMethod.DELETE, path = "/remove") //TODO
	@ResponseBody
	public String removeEvent(Events event)
	{
		int eventId = event.geteventid();
		eventRepository.delete(event);
		return "Event " + eventId +" has been deleted";
	}
	
	/**
	 * Gets all the users that are associated with any events
	 * @return
	 * Returns a list of all the users that have an event
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/all")
	@ResponseBody
	public List<Events> getAllUsers(){
		logger.info("Entered into Controller Layer");
		List<Events> results = (List<Events>) eventRepository.findAll();
		logger.info("Number of records fetched:" + results.size());
		return results;
	}

	/**
	 * Gets an event by its associated id
	 * @param EventId
	 * @return
	 * Returns the event
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/{eventId}")
	@ResponseBody
	public Optional<Events> findEventById(@PathVariable("eventId") int EventId){
		logger.info("Entered into Controller Layer");
		Optional<Events> results = eventRepository.findById(EventId);
		return results;
	}

	/**
	 * Returns the events associated with a users id
	 * @param userId
	 * @return
	 * Returns all the events for a particular user
	 */
	@GetMapping(path = "/user/{userId}")
	@ResponseBody
	public Optional<Events> findEventByUserId(@PathVariable("userId") int userId)
	{
		logger.info("Entered into findEventByUserId method");
		Optional<Events> results = eventRepository.findById(userId);
		return results;
	}
	
		
}
