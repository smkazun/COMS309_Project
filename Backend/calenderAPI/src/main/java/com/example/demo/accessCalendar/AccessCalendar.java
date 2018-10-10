package com.example.demo.accessCalendar;

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
@Table(name = "accessCalander")
public class AccessCalendar {
	
	@Id
	@Column(name = "ClientId")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer ClientId;
	
	@Column(name = "CalanderId")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer CalanderId;
	
	public Integer getClientID() {
		return ClientId;
	}
	
	public void setClientId(Integer id) {
		this.ClientId = id;
	}
	
	
	public Integer getCalanderId() {
		return CalanderId;
	}
	
	public void setCalendarId(Integer id) {
		this.CalanderId = id;
	}
	
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
				
				.append("CalanderId", this.getClientID())
				.append("CalanderId", this.getCalanderId()).toString();
		
	}

}