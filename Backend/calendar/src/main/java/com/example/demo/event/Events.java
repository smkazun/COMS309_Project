package com.example.demo.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "Event")
public class Events {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer eventid;
	
	private Integer calendarid;
	
	private String eventname;
	
	private String date;
	
	private String time;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "calendarid")
	
	public Integer geteventid() {
		return eventid;
	}
	
	public Integer getcalendarid() {
		return calendarid;
	}
	
	public String geteventname() {
		return eventname;
	}
	
	public String getdate() {
		return date;
	}
	
	public String gettime() {
		return time;
	}
	
	public void seteventname(String name) {
		this.eventname = name;
	}
	
	public void setdate(String date) {
		this.date = date;
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
