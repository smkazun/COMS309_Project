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
	
	@Column(name = "calendarUsers")
	@NotFound(action = NotFoundAction.IGNORE)
	@ElementCollection(targetClass=Calendar.class)
	private List<Calendar> calendarUsers;
	
	
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
	
	public List<Calendar> getCalendarUsers() {
		return this.calendarUsers;
	}
	
	public void setCalendarUsers(List<Calendar> calendarUsers) {
		for(int i = 0; i < calendarUsers.size(); ++i)
		{
			this.calendarUsers = calendarUsers;
		}
		//.calendarUsers = calendarUsers;
	}
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
				
				.append("calendarId", this.getCalendarId())
				.append("calendarName", this.getCalendarName())
				.append("calenderUsers", this.getCalendarUsers()).toString();
		
		
	}
	

}
