package com.example.demo.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.example.demo.calendar.Calendar;

/**
 * 
 * @author Sebastian Kazun
 *
 */
@Entity
@Table(name = "users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userid;
	
	private String name;

	private String email;

	private String usertype; //private UserType userType;
	
	@ManyToMany(cascade= {CascadeType.ALL})
	@JoinTable(name="friendlist",
		joinColumns= {@JoinColumn(name = "userid")},
		inverseJoinColumns= {@JoinColumn(name="friend")})
	private Set<Users> friends = new HashSet<Users>();
	
	@ManyToMany(mappedBy = "friends")
	private Set<Users> userfriends = new HashSet<Users>();
    
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
	
