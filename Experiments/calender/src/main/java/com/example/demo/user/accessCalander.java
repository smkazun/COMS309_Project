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
@Table(name = "accessCalander")
public class accessCalander {
	
	@Id
	@Column(name = "InstructorId")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer InstructorId;
	
	@Id
	@Column(name = "CalanderId")
	@NotFound(action = NotFoundAction.IGNORE)
	private Integer CalanderId;
	
	public Integer getInstructorID() {
		return InstructorId;
	}
	
	public void setInstructorId(Integer id) {
		this.InstructorId = id;
	}
	
	
	public Integer getCalanderId() {
		return CalanderId;
	}
	
	
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
				
				.append("CalanderId", this.getInstructorID())
				.append("CalanderId", this.getCalanderId()).toString();
		
	}

}
