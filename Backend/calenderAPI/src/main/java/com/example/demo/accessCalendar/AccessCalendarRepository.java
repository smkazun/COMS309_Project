package com.example.demo.accessCalendar;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessCalendarRepository extends CrudRepository<AccessCalendar, Integer>{


}
