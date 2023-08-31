package com.tech.mma.controller;

import com.tech.mma.model.Fighter;
import com.tech.mma.repo.FighterRepository;
import com.tech.mma.service.RankingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/ranking")
public class RankingsController {

    private final RankingsService rankingsService;
    private final FighterRepository fighterRepository;

    @Autowired
    public RankingsController(RankingsService rankingsService, FighterRepository fighterRepository) {
        this.rankingsService = rankingsService;
        this.fighterRepository = fighterRepository;
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateRankingsAfterFight(@PathVariable Long id) {
        Optional<Fighter> optionalFighter = fighterRepository.findById(id);

        if (optionalFighter.isEmpty()) {
            return ResponseEntity.badRequest().body("Fighter not found.");
        }

        Fighter fighter = optionalFighter.get();
        rankingsService.updateFighterRankingsAfterFight(fighter);
        return ResponseEntity.ok("Rankings updated successfully after the fight.");
    }
    @GetMapping("/update-all")
    public ResponseEntity<String> updateAllFightersRankings() {
        rankingsService.updateAllFightersRankings();
        return ResponseEntity.ok("Rankings updated successfully for all fighters.");
    }

}
