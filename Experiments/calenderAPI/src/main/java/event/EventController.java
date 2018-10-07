package event;

import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.user.UserRepository;
import com.example.demo.user.Users;
//import com.example.demo.user.event;



@RestController
//@RequestMapping(path = "/events")
public class EventController {
	
	@Autowired
	private EventRepository eventRepository;
	
	private final Logger logger = LoggerFactory.getLogger(EventController.class);
	
	//save new event
	@RequestMapping(method = RequestMethod.POST, path = "/events/new")
	public String saveEvent(Events event) {
		eventRepository.save(event);
		return "New event saved";
		
	}
	
	//gets all events
	@RequestMapping(method = RequestMethod.GET, path = "/events")
	public List<Events> getAllEvents() {
		logger.info("Entered into Controller layer");
		List<Events> results =  (List<Events>) eventRepository.findAll();
		logger.info("number of records fetched: " + results.size());
		return results;
	}
		
	@RequestMapping(method = RequestMethod.GET, path = "/events/{eventId}")
	public Optional<Events> findEventById(@PathVariable("eventId") int EventId){
		logger.info("Entered into Controller Layer");
		Optional<Events> results = eventRepository.findById(EventId);
		return results;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/events/{calendarId}")
	public Optional<Events> findCalendarById(@PathVariable("calendarId") int CalendarId){
		logger.info("Entered into Controller Layer");
		Optional<Events> results = eventRepository.findById(CalendarId);
		return results;
		
	}
	

	
	
}
