package com.tech.mma.service;

import com.tech.mma.dto.EventDto;
import com.tech.mma.model.Event;
import com.tech.mma.repo.EventRepository;
import com.tech.mma.utility.EventNotFoundExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<?> getAllEvents() {
        List<Event> allEvents = eventRepository.findAll();

        return allEvents;
    }

    public Event addEvent(EventDto eventDto) {
        try {
            Event event = new Event();
            event.setEventTitle(eventDto.getEventTitle());
            event.setEventDate(eventDto.getEventDate());
            event.setLocation(eventDto.getLocation());
            return eventRepository.save(event);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Event updateEvent(Long id, EventDto eventDto) {
        Optional<Event> optionalEvent = eventRepository.findById(id);

        if (optionalEvent.isEmpty()) {
            throw new EventNotFoundExecption("Event with ID " + id + " not found");
        }

        Event event = optionalEvent.get();
        event.setEventTitle(eventDto.getEventTitle());
        event.setEventDate(eventDto.getEventDate());
        event.setLocation(eventDto.getLocation());

        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isEmpty()) {
            throw new EventNotFoundExecption("Event with ID " + id + " not found");
        }
        eventRepository.delete(optionalEvent.get());
    }




}
