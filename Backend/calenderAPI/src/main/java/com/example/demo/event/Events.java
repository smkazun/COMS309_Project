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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EventId")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer EventId;
	
	@Column(name = "CalendarId") //fix spelling TODO
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer CalendarId;
	
	@Column(name =  "Time")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer Time;
	
	public Integer getEventId() {
		return EventId;
	}
	
	public Integer getCalendarId() {
		return CalendarId;
	}
	
	public Integer getTime() {
		return Time;
	}
	
	public void setTime (Integer Time) {
		this.Time = Time;
	}
	
	public void setEventId(Integer Id) {
		this.EventId = Id;
	}
	
	public void setCalendarId(Integer Id) {
		this.CalendarId = Id;
	}

	@Override
	public String toString() {
		return new ToStringCreator(this)
				
				.append("EventId", this.getEventId())
				.append("CalanderId", this.getCalendarId())
				.append("Time", this.getTime()).toString();
		
	}
	
}
