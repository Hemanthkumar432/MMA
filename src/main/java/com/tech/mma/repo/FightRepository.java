package com.tech.mma.repo;

import com.tech.mma.model.Event;
import com.tech.mma.model.Fight;
import com.tech.mma.model.FightOutcome;
import com.tech.mma.model.Fighter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FightRepository extends JpaRepository<Fight,Long> {
    Optional<Fight> findById(Long id);
    List<Fight> findByFighter(Fighter fighter);
    FightOutcome findFightOutcomeByFighter(Fighter fighter);

    List<Fight> findAllByFighter(Fighter fighter);
    List<Fight> findUpcomingFightsByFighterAndFightDateAfterOrderByFightDateAsc(Fighter fighter, Date currentDate);

}
