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

import com.example.demo.user.*;

@Entity
@Table(name = "calendar")
public class Calendar {


	@Id
	private Integer calendarId;

	private String calendarName;
	
	@JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "AccessCalendar",
			joinColumns = { @JoinColumn(name = "calendarId")},
			inverseJoinColumns = { @JoinColumn(name = "Userid") })
	private Set<Users> users = new HashSet<>();
	
	public Integer getcalendarId() {
		return calendarId;
	}
	
	public void setcalendarId(Integer calendarId) {
		this.calendarId = calendarId;
	}
	
	public String getCalendarName() {
		return calendarName;
	}
	
	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}
	
	public Set<Users> getUsers(){
		return users;
	}
	
	public void setUsers(Set<Users> users) {
		this.users = users;
	}
	
	public Map<String, Object> toDTO()
	{
		Map<String,Object> map = new HashMap<>();
		map.put("calendarId", this.calendarId);
		
		Set<Map<String,Object>> usersDTO = new HashSet<>();
		for(Users u : users) {
			Map<String,Object> usersMap = new HashMap<>();
			usersMap.put("Userid", u.getUserid());
			usersDTO.add(usersMap);
		}
		map.put("Users", usersDTO);
		
		return map;
	}
	
}