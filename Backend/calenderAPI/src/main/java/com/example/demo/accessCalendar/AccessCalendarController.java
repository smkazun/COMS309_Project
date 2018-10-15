package com.example.demo.accessCalendar;

import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.accessCalendar.AccessCalendar;
import com.example.demo.accessCalendar.AccessCalendarRepository;

import com.example.demo.calendar.CalendarRepository;
import com.example.demo.calendar.Calendar;
import com.example.demo.calendar.CalendarController;

import com.example.demo.user.Users;
import com.example.demo.user.UserController;
import com.example.demo.user.UserRepository;




@RestController
@RequestMapping(path = "/AccessCalendar")
public class AccessCalendarController {
	
	@Autowired
	private AccessCalendarRepository AccessCalendarRepository;
	@Autowired
	private CalendarRepository calendarRepository;
	@Autowired
	private UserRepository UserRepository;
	
	//accessCalendar logger
	private final Logger alogger = LoggerFactory.getLogger(AccessCalendarController.class);
	//user logger
	private final Logger ulogger = LoggerFactory.getLogger(CalendarController.class);
	//Calendar logger
	private final Logger clogger = LoggerFactory.getLogger(UserController.class);
	
	
	//save new event
	@RequestMapping(method = RequestMethod.POST, path = "/new")
	public @ResponseBody String saveEvent(@RequestBody AccessCalendar Access) {
		AccessCalendarRepository.save(Access);
		return "New event saved";
	}
	
	//get all info about AccessCalendar
	@RequestMapping(method = RequestMethod.GET, path = "/AccessInfo")
	@ResponseBody
	public String getAllAccessInfo(@RequestBody AccessCalendar Access)
	{	
		return "calendarId: " + Access.getCalanderId() + "\nClientId: " + Access.getClientID();
	}
	
	//get names of all users connected to calendar
	@RequestMapping(method = RequestMethod.GET, path = "/getAllNames/{calendarId}")
	@ResponseBody
	public List<Users> findUserById(@PathVariable("CalanderId") Integer id)
	{
		List<Users> results = UserRepository.getAllNames(id);
		return results;
	}
}