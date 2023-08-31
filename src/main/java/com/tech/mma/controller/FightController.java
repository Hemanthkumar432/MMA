package com.tech.mma.controller;

import com.tech.mma.dto.EventDto;
import com.tech.mma.dto.FightDto;
import com.tech.mma.dto.FighterResponseDTO;
import com.tech.mma.model.Fight;
import com.tech.mma.model.Fighter;
import com.tech.mma.repo.FighterRepository;
import com.tech.mma.service.FightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/fight")
public class FightController {

    private final FightService fightService;
    private final FighterRepository fighterRepository;

    @Autowired
    public FightController(FightService fightService, FighterRepository fighterRepository) {
        this.fightService = fightService;
        this.fighterRepository = fighterRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Fight>> getAllFights() {
        List<Fight> fights = fightService.getAllFights();
        return new ResponseEntity<>(fights, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addFight(@RequestBody FightDto fightDto) {
        try {
            Fight addedFight = fightService.addFight(fightDto);
            return new ResponseEntity<>(addedFight, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid fighter or opponent nickname.", HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Failed to add fight.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("edit/{fightId}")
    public ResponseEntity<Object> updateFight(@PathVariable Long fightId, @RequestBody FightDto fightDto) {
        try {
            Fight updatedFight = fightService.updateFight(fightId, fightDto);
            return new ResponseEntity<>(updatedFight, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid input or fight not found.", HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Failed to update fight.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete/{fightId}")
    public ResponseEntity<Object> deleteFight(@PathVariable Long fightId) {
        try {
            fightService.deleteFight(fightId);
            return new ResponseEntity<>("Fight deleted successfully.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Fight not found.", HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Failed to delete fight.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/fight-statistics/{id}")
    public List<Fight> getAllFightsByFighterId(@PathVariable Long id) {
        Optional<Fighter> optionalFighter = fighterRepository.findById(id);
        if (!optionalFighter.isPresent()) {
            throw new IllegalArgumentException("Fighter not found.");
        }

        Fighter fighter = optionalFighter.get();
        return fightService.getAllFightsByFighterId(fighter.getId());
    }

    @GetMapping("/upcoming-fights/{id}")
    public ResponseEntity<?> getUpcomingFightsForFighter(@PathVariable long id) {
        Optional<Fighter> optionalFighter = fighterRepository.findById(id);
        if (optionalFighter.isEmpty()) {
            return ResponseEntity.badRequest().body("Fighter not found.");
        }
        Fighter fighterId = optionalFighter.get();
        List<FighterResponseDTO> upcomingFights = fightService.getUpcomingFightsForFighter(fighterId);
        return ResponseEntity.ok(upcomingFights);
    }

}
