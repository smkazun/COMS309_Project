package event;

import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.user.UserRepository;
import com.example.demo.user.Users;
//import com.example.demo.user.event;



@RestController
@RequestMapping(path = "/events")
public class EventController {
	
	@Autowired
	private EventRepository eventRepository;
	
	private final Logger logger = LoggerFactory.getLogger(EventController.class);
	
	//save new event
	@RequestMapping(method = RequestMethod.POST, path = "/new")
	public String saveEvent(Events event) {
		eventRepository.save(event);
		return "New event saved";
	}
	
	//different save event implementation
	@RequestMapping(method = RequestMethod.POST, path = "/new2") //fix mapping TODO
	public String saveEvent(@RequestParam Integer eventId, @RequestParam Integer calanderId, @RequestParam Integer Time) {
		Events event = new Events();
		event.setCalanderId(calanderId);  //fix calendar spelling TODO
		event.setEventId(eventId);
		event.setTime(Time);
		eventRepository.save(event);
		return "New event saved";
	}
	
	//get all info about one event
	@RequestMapping(method = RequestMethod.GET, path = "/eventInfo")
	public String getAllEventInfo(Events event)
	{	
		return "calendarId: " + event.getCalanderId() + "\neventId: " + event.getEventId() + "\ntime: " + event.getTime();
	}
	
	//removes an event
	@RequestMapping(method = RequestMethod.POST, path = "/remove")
	public String removeEvent(Events event)
	{
		int eventId = event.getEventId();
		eventRepository.delete(event);
		return "Event " + eventId +" has been deleted";
	}
	
	
	//gets all events
	@RequestMapping(method = RequestMethod.GET, path = "/all")
	public List<Events> getAllEvents() {
		logger.info("Entered into Controller layer");
		List<Events> results =  (List<Events>) eventRepository.findAll();
		logger.info("number of records fetched: " + results.size());
		return results;
	}
		
	@RequestMapping(method = RequestMethod.GET, path = "/{eventId}")
	public Optional<Events> findEventById(@PathVariable("eventId") int EventId){
		logger.info("Entered into Controller Layer");
		Optional<Events> results = eventRepository.findById(EventId);
		return results;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/{calendarId}")
	public Optional<Events> findCalendarById(@PathVariable("calendarId") int CalendarId){
		logger.info("Entered into Controller Layer");
		Optional<Events> results = eventRepository.findById(CalendarId);
		return results;
		
	}
	

	
	
}
