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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.event.Events;
import com.example.demo.event.EventRepository;



@RestController
@RequestMapping(path = "/event")
public class EventController {
	
	@Autowired
	private EventRepository eventRepository;
	
	private final Logger logger = LoggerFactory.getLogger(EventController.class);
	
	//save new event
	@RequestMapping(method = RequestMethod.POST, path = "/new")
	public @ResponseBody String saveEvent(@RequestBody Events event) {
		eventRepository.save(event);
		return "New event saved";
	}
	/*
	//different save event implementation
	@RequestMapping(method = RequestMethod.POST, path = "/new2") //fix mapping TODO
	public @ResponseBody String saveEvent(@RequestParam Integer eventId, @RequestParam Integer calanderId, @RequestParam Integer Time) {
		Events event = new Events();
		event.setCalanderId(calanderId);  //fix calendar spelling TODO
		event.setEventId(eventId);
		event.setTime(Time);
		eventRepository.save(event);
		return "New event saved";
	}
	*/
	
	//get all info about one event
	@RequestMapping(method = RequestMethod.GET, path = "/eventInfo")
	@ResponseBody
	public String getAllEventInfo(@RequestBody Events event)
	{	
		return "calendarId: " + event.getCalendarId() + "\neventId: " + event.getEventId() + "\ntime: " + event.getTime();
	}
	
	//removes an event
	@RequestMapping(method = RequestMethod.POST, path = "/remove")
	@ResponseBody
	public String removeEvent(Events event)
	{
		int eventId = event.getEventId();
		eventRepository.delete(event);
		return "Event " + eventId +" has been deleted";
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/all")
	@ResponseBody
	public List<Events> getAllUsers(){
		logger.info("Entered into Controller Layer");
		List<Events> results = (List<Events>) eventRepository.findAll(); //?
		logger.info("Number of records fetched:" + results.size());
		return results;
	}

		
	@RequestMapping(method = RequestMethod.GET, path = "/{eventId}")
	@ResponseBody
	public Optional<Events> findEventById(@PathVariable("eventId") int EventId){
		logger.info("Entered into Controller Layer");
		Optional<Events> results = eventRepository.findById(EventId);
		return results;
	}
	
		
}
