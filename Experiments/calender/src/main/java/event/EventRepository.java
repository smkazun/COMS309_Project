package event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import com.example.demo.user.Users;
import com.example.demo.user.event;

@Repository
public interface EventRepository extends CrudRepository<event, Integer> {

	//void save(Events event);

}
