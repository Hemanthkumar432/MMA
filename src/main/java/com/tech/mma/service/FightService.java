package com.tech.mma.service;

import com.tech.mma.dto.EventDto;
import com.tech.mma.dto.FightDto;
import com.tech.mma.dto.FighterResponseDTO;
import com.tech.mma.model.*;
import com.tech.mma.repo.EventRepository;
import com.tech.mma.repo.FightRepository;
import com.tech.mma.repo.FighterRepository;
import com.tech.mma.repo.RankingsRepository;
import com.tech.mma.utility.EventNotFoundExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FightService {

    private final FightRepository fightRepository;
    private final FighterRepository fighterRepository;
    private final EventRepository eventRepository;

    private final RankingsRepository rankingsRepository;

    @Autowired
    public FightService(FightRepository fightRepository, FighterRepository fighterRepository, EventRepository eventRepository, RankingsRepository rankingsRepository) {
        this.fightRepository = fightRepository;
        this.fighterRepository = fighterRepository;
        this.eventRepository = eventRepository;
        this.rankingsRepository = rankingsRepository;
    }

    public List<Fight> getAllFights() {
        return fightRepository.findAll();
    }

    public Fight addFight(FightDto fightDto) {
        try {
            Fight fight = new Fight();

            Fighter fighter = fighterRepository.findByNickName(fightDto.getFighterNickName());
            Fighter opponent = fighterRepository.findByNickName(fightDto.getOpponentNickName());
            Event event = eventRepository.findByEventTitle(fightDto.getEvent());

            if (fighter == null || opponent == null) {
                throw new IllegalArgumentException("Fighter or opponent not found.");
            }
            else if(event == null){
                throw new EventNotFoundExecption("event not found");
            }

            fight.setFighter(fighter);
            fight.setOpponent(opponent);
            fight.setFightDate(event.getEventDate());
            fight.setRound(fightDto.getRound());
            fight.setTiming(fightDto.getTiming());
            fight.setEvent(event);
            fight.setLocation(event.getLocation());
            FightOutcome outcome = fightDto.getFightOutcome();
            fight.setFightOutcome(outcome);

            return fightRepository.save(fight);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add fight.");
        }
    }

    public Fight updateFight(Long fightId, FightDto fightDto) {
        Optional<Fight> optionalFight = fightRepository.findById(fightId);
        if (optionalFight.isEmpty()) {
            throw new IllegalArgumentException("Fight not found.");
        }

        Fight fight = optionalFight.get();

        Fighter fighter = fighterRepository.findByNickName(fightDto.getFighterNickName());
        Fighter opponent = fighterRepository.findByNickName(fightDto.getOpponentNickName());
        Event event = eventRepository.findByEventTitle(fightDto.getEvent());

        if (fighter == null || opponent == null) {
            throw new IllegalArgumentException("Fighter or opponent not found.");
        }
        else if(event == null){
            throw new EventNotFoundExecption("event not found");
        }

        fight.setFighter(fighter);
        fight.setOpponent(opponent);
        fight.setFightDate(event.getEventDate());
        fight.setRound(fightDto.getRound());
        fight.setTiming(fightDto.getTiming());
        fight.setEvent(event);
        fight.setLocation(event.getLocation());
        FightOutcome outcome = fightDto.getFightOutcome();
        fight.setFightOutcome(outcome);

        return fightRepository.save(fight);
    }

    public void deleteFight(Long fightId) {
        if (!fightRepository.existsById(fightId)) {
            throw new IllegalArgumentException("Fight not found.");
        }

        fightRepository.deleteById(fightId);
    }
    public List<Fight> getAllFightsByFighterId(Long id) {
        Optional<Fighter> optionalFighter = fighterRepository.findById(id);
        if (!optionalFighter.isPresent()) {
            throw new IllegalArgumentException("Fighter not found.");
        }

        Fighter fighter = optionalFighter.get();
        return fightRepository.findAllByFighter(fighter);
    }

    public List<FighterResponseDTO> getUpcomingFightsForFighter(Fighter fighterId) {
        java.util.Date currentDate = new Date();
        List<Fight> upcomingFights = fightRepository.findUpcomingFightsByFighterAndFightDateAfterOrderByFightDateAsc(fighterId, currentDate);
        List<FighterResponseDTO> fightDtoList = new ArrayList<>();

        for (Fight fr : upcomingFights) {
            FighterResponseDTO fighterResponseDTO = new FighterResponseDTO();
            fighterResponseDTO.setFightDate(fr.getFightDate());
            fighterResponseDTO.setLocation(fr.getLocation());
            fighterResponseDTO.setOpponent(fr.getOpponent().getId());
            fighterResponseDTO.setTiming(fr.getTiming());

            fightDtoList.add(fighterResponseDTO);
        }
        if (fightDtoList.isEmpty()) {
            throw new RuntimeException("No upcoming fights found for the fighter.");
        }

        return fightDtoList;
    }


}
