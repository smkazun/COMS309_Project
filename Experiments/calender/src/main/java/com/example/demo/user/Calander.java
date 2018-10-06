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
@Table(name = "Calander")
public class Calander {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CalanderID")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer CalanderId;

	@Column(name = "CalanderName")
	@NotFound(action = NotFoundAction.IGNORE)
	private String CalanderName;
	
	public String getCalanderName() {
		return CalanderName;
	}
	
	public void setCalanderName(String Name) {
		this.CalanderName = Name;
	}
}
