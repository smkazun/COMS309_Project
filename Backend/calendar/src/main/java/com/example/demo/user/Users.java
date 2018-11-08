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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userid;
	
	private String name;

	private String email;

	private String usertype; //private UserType userType;
	
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "users")
	private Set<Calendar> calendars = new HashSet<>();
    
    	public Integer getuserid() {
			return userid;
		}	
	
		public void setuserid(Integer id) {
			this.userid = id;
		}
	
		public String getname() {
			return name;
		}
	
		public void setname(String name) {
			this.name = name;
		}
	
		public String getemail() {
			return this.email;
		}
	
		public void setemail(String email) {
			this.email = email;
		}
	
		public String getusertype() {
			return this.usertype;
		}
	
		public void setusertype(String userType) {
			this.usertype = userType;
		}
		
		public Set<Calendar> getcalendars(){
			return calendars;
		}
		
		public void setcalendars(Set<Calendar> calendars) {
			this.calendars = calendars;
		}
		
}
	
