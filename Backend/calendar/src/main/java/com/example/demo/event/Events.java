package com.example.demo.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "Event")
public class Events {
	
	@Id
	private Integer eventid;
	
	private Integer calendarid;
	
	private String time;
	
	public Integer geteventid() {
		return eventid;
	}
	
	public Integer getcalendarid() {
		return calendarid;
	}
	
	public String gettime() {
		return time;
	}
	
	public void settime (String Time) {
		this.time = Time;
	}
	
	public void seteventid(Integer Id) {
		this.eventid = Id;
	}
	
	public void setcalendarid(Integer Id) {
		this.calendarid = Id;
	}
	
}
