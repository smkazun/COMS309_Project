package com.example.demo.event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.event.Events;

@Repository
public interface EventRepository extends CrudRepository<Events, Integer> {


}
