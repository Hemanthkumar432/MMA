package com.tech.mma.controller;

import com.tech.mma.dto.EventDto;
import com.tech.mma.dto.FighterDto;
import com.tech.mma.model.Fighter;
import com.tech.mma.service.FighterService;
import com.tech.mma.utility.FighterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/fighter")
public class FighterController {

    @Autowired
    private final FighterService fighterService;

    public FighterController(FighterService fighterService) {
        this.fighterService = fighterService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllFighters() {
        try{

        List<Fighter> allFighters = fighterService.getAllFighters();
        return ResponseEntity.ok(allFighters);

    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve Fighters.");
    }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFighter( @RequestBody FighterDto fighterDto) {
        Fighter addedFighter = fighterService.addFighter(fighterDto);
        if (addedFighter != null) {
            return ResponseEntity.ok("Fighter added successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add fighter.");
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateFighter(@PathVariable Long id, @RequestBody FighterDto fighterDto) {
        try {
            Fighter updatedFighter = fighterService.updateFighter(id, fighterDto);
            return ResponseEntity.ok("Fighter updated successfully!");
        } catch (FighterNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fighter not found.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update fighter.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFighter(@PathVariable Long id) {
        try {
            fighterService.deleteFighter(id);
            return ResponseEntity.ok("Fighter deleted successfully!");
        } catch (FighterNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fighter not found.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete fighter.");
        }
    }
}
