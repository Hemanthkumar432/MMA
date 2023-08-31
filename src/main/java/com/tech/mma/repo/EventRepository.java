package com.tech.mma.repo;

import com.tech.mma.model.Event;
import com.tech.mma.model.Fighter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByEventTitle(String event);



}
