package event;
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

import com.example.demo.user.Calendar;
import com.example.demo.user.UserRepository;
import com.example.demo.user.Users;
import event.CalendarRepository;



@RestController
@RequestMapping(path = "/calendar")
public class CalendarController {
	
	@Autowired
	private CalendarRepository calendarRepository;
	
	private final Logger logger = LoggerFactory.getLogger(CalendarController.class);
	
	//make new calander
	@RequestMapping(method = RequestMethod.POST, path = "/calendar/new")
	public @ResponseBody String saveCalendar(@RequestBody Calendar calendar) {
		calendarRepository.save(calendar);
		return "New Calendar " + calendar.getCalendarName() + " saved";
		
	}
	
	//gets all calendar
	@RequestMapping(method = RequestMethod.GET, path = "/calendar")
	public List<Calendar> getAllcalendar() {
		logger.info("Entered into Controller layer");
		List<Calendar> results =  (List<Calendar>) calendarRepository.findAll();
		logger.info("number of records fetched: " + results.size());
		return results;
	}
		
	
	
	
}