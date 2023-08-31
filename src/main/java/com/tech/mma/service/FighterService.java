package com.tech.mma.service;

import com.tech.mma.dto.EventDto;
import com.tech.mma.dto.FightStatisticsDTO;
import com.tech.mma.dto.FighterDto;
import com.tech.mma.model.Event;
import com.tech.mma.model.Fight;
import com.tech.mma.model.Fighter;
import com.tech.mma.repo.EventRepository;
import com.tech.mma.repo.FightRepository;
import com.tech.mma.repo.FighterRepository;
import com.tech.mma.utility.FighterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FighterService {


    private final FighterRepository fighterRepository;
    private final FightRepository fightRepository;

    private EventRepository eventRepository;

    @Autowired
    public FighterService(FighterRepository fighterRepository, FightRepository fightRepository) {
        this.fighterRepository = fighterRepository;

        this.fightRepository = fightRepository;
    }

    public List<Fighter> getAllFighters() {
        List<Fighter> allFighters = fighterRepository.findAll();
        return allFighters;
    }

    public Fighter addFighter(FighterDto fighterDto) {
        try {
            Fighter fighter = new Fighter();
            fighter.setFullName(fighterDto.getFullName());
            fighter.setNickName(fighterDto.getNickName());
            fighter.setAge(fighterDto.getAge());
            fighter.setWeight(fighterDto.getWeight());
            fighter.setHeight(fighterDto.getHeight());
            fighter.setWeightClass(fighterDto.getWeightClass());
            fighter.setNationality(fighterDto.getNationality());
            fighter.setTeamAffiliation(fighterDto.getTeamAffiliation());
            return fighterRepository.save(fighter);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Fighter updateFighter(Long id, FighterDto fighterDto) {
        Optional<Fighter> optionalFighter = fighterRepository.findById(id);

        if (optionalFighter.isEmpty()) {
            throw new FighterNotFoundException("Fighter with ID " + id + " not found");
        }

        Fighter fighter = optionalFighter.get();
        fighter.setFullName(fighterDto.getFullName());
        fighter.setNickName(fighterDto.getNickName());
        fighter.setAge(fighterDto.getAge());
        fighter.setWeight(fighterDto.getWeight());
        fighter.setHeight(fighterDto.getHeight());
        fighter.setWeightClass(fighterDto.getWeightClass());
        fighter.setNationality(fighterDto.getNationality());
        fighter.setTeamAffiliation(fighterDto.getTeamAffiliation());

        return fighterRepository.save(fighter);
    }


    public void deleteFighter(Long id) {
        Optional<Fighter> optionalFighter = fighterRepository.findById(id);
        if (optionalFighter.isEmpty()) {
            throw new FighterNotFoundException("Fighter with ID " + id + " not found");
        }
        fighterRepository.delete(optionalFighter.get());
    }


}
