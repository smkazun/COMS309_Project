package com.example.demo.calendar;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "calendar")
public class Calendar {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "calendarId")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer calendarId;
	
	@Column(name = "calendarName")
	@NotFound(action = NotFoundAction.IGNORE)
	private String calendarName;
	
	
	public Integer getCalendarId() {
		return calendarId;
	}
	
	public void setCalendarId(Integer calendarId) {
		this.calendarId = calendarId;
	}
	
	public String getCalendarName() {
		return calendarName;
	}
	
	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
				
				.append("calendarId", this.getCalendarId())
				.append("calendarName", this.getCalendarName()).toString();
		
		
	}
	

}
