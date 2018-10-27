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
@Table(name = "users")
public class Users {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer id;
	
	@Column(name = "name")
	@NotFound(action = NotFoundAction.IGNORE)
	private String name;
	
	@Column(name = "email")
	@NotFound(action = NotFoundAction.IGNORE)
	private String email;
	
	@Column(name = "user_type")
	@NotFound(action = NotFoundAction.IGNORE)
	private String userType; //private UserType userType;
	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
				
				.append("id", this.getId())
				.append("name", this.getName())
				.append("type", this.getUserType())
				.append("email", this.getEmail()).toString();
		
		
	}
	
}
