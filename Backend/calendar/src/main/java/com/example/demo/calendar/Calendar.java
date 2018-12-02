package com.example.demo.calendar;

import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

import com.example.demo.event.Events;
import com.example.demo.user.*;

/**
 * 
 * @author Sebastian Kazun
 *
 */
@Entity
@Table(name = "calendar")
public class Calendar {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer calendarid;

	private String calendarname;
		
	@JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "accesscalendar",
			joinColumns = { @JoinColumn(name = "calendarid")},
			inverseJoinColumns = { @JoinColumn(name = "userid") })
	private Set<Users> users = new HashSet<>();
	
	private Set<Events> events;
	
	public Integer getcalendarid() {
		return calendarid;
	}
	
	public void setcalendarid(Integer calendarId) {
		this.calendarid = calendarId;
	}
	
	public String getcalendarname() {
		return calendarname;
	}
	
	public void setcalendarname(String calendarName) {
		this.calendarname = calendarName;
	}
	
	public Set<Users> getusers(){
		return users;
	}
	
	public void setusers(Set<Users> users) {
		this.users = users;
	}
	
	public Set<Events> getEvents() {
        return events;
    }
	
	public void setEvent(Set<Events> events) {
		this.events = events;
	}
	
	public Map<String, Object> toDTO(int it)
	{
		Map<String,Object> map = new HashMap<>();
		map.put("calendarid", this.calendarid);
		
		Set<Map<String,Object>> usersDTO = new HashSet<>();
		for(Users u : users) {
			if(u.getuserid() == it) {
				Map<String,Object> usersMap = new HashMap<>();
				usersMap.put("userid", u.getuserid());
				usersDTO.add(usersMap);
			}
		}
		map.put("users", usersDTO);
		
		return map;
	}
	
}
