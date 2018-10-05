package event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.example.demo.user.Users;

@Entity
@Table(name = "events")
public class Events {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "startDate")
	//@NotFound(action = NotFoundAction.IGNORE)
	//private Time startDate;
	
	//@Column(name = "endDate")
	//@NotFound(action = NotFoundAction.IGNORE)
	//private Time endDate;
	
	//@Column(name = "admin")
	//@NotFound(action = NotFoundAction.IGNORE)
	private Users admin;
	


}
