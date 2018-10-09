package event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import com.example.demo.user.Users;

@Repository
public interface EventRepository extends CrudRepository<Events, Integer> {

	//void save(Events event);

}
