package event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.user.Calendar;
@Repository
public interface CalendarRepository extends CrudRepository<Calendar, Integer> {


	//void save(Calendar calendarName);

	//void save(Calendar calendar);

}
