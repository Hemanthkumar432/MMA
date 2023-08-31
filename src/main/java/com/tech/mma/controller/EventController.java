package com.tech.mma.controller;

import com.tech.mma.dto.EventDto;
import com.tech.mma.model.Event;
import com.tech.mma.service.EventService;
import com.tech.mma.utility.EventNotFoundExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/event")
public class EventController {

    @Autowired
    public EventService eventService;


    @GetMapping("/all")
    public ResponseEntity<?> getAllEvents() {
        try {
            List<Event> allEvents = (List<Event>) eventService.getAllEvents();
            return ResponseEntity.ok(allEvents);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve events.");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEvent(@RequestBody EventDto eventDto) {
        Event addedEvent = eventService.addEvent(eventDto);
        if (addedEvent != null) {
            return ResponseEntity.ok("Event added successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add event.");
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody EventDto eventDto) {
        try {
            Event updatedEvent = eventService.updateEvent(id, eventDto);
            return ResponseEntity.ok("Event updated successfully!");
        } catch (EventNotFoundExecption e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update event.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        try {
            eventService.deleteEvent(id);
            return ResponseEntity.ok("Event deleted successfully!");
        } catch (EventNotFoundExecption e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("event not found.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete event.");
        }
    }
}
