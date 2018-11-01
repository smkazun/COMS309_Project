package com.example.demo.user;

import javax.persistence.Column;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.example.demo.calendar.Calendar;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userid;
	
	private String name;

	private String email;

	private String userType; //private UserType userType;
	
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "users")
	private Set<Calendar> calendars = new HashSet<>();
    
    	public Integer getuserid() {
			return userid;
		}	
	
		public void setUserid(Integer id) {
			this.userid = id;
		}
	
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
	
		public String getEmail() {
			return this.email;
		}
	
		public void setEmail(String email) {
			this.email = email;
		}
	
		public String getUserType() {
			return this.userType;
		}
	
		public void setUserType(String userType) {
			this.userType = userType;
		}
		
		public Set<Calendar> getCalendars(){
			return calendars;
		}
		
		public void setCalendars(Set<Calendar> calendars) {
			this.calendars = calendars;
		}
		
}
	
