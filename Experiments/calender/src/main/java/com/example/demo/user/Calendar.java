package com.example.demo.user;

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
@Table(name = "Calendar")
public class Calendar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CalendarID")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer CalendarId;

	@Column(name = "CalendarName")
	@NotFound(action = NotFoundAction.IGNORE)
	private String CalendarName;
	
	public Integer getCalendarId() {
		return CalendarId;
	}
	
	public String getCalendarName() {
		return CalendarName;
	}
	
	public Integer setCalendarId(Integer CalendarId) {
		return this.CalendarId = CalendarId;
	}
	
	public void setCalendarName(String Name) {
		this.CalendarName = Name;
	}
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
				
				.append("CalendarId", this.getCalendarId())
				.append("CalendarName", this.getCalendarName()).toString();
		
		
	}
}
