package com.tech.mma.service;

import com.tech.mma.model.Fight;
import com.tech.mma.model.FightOutcome;
import com.tech.mma.model.Fighter;
import com.tech.mma.model.Rankings;
import com.tech.mma.repo.FightRepository;
import com.tech.mma.repo.FighterRepository;
import com.tech.mma.repo.RankingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class RankingsService {

    private final RankingsRepository rankingsRepository;
    private final FighterRepository fighterRepository;
    private final FightRepository fighteRepository;

    @Autowired
    public RankingsService(RankingsRepository rankingsRepository, FighterRepository fighterRepository, FightRepository fighteRepository) {
        this.rankingsRepository = rankingsRepository;
        this.fighterRepository = fighterRepository;
        this.fighteRepository = fighteRepository;
    }

    public void updateAllFightersRankings() {
        List<Fighter> allFighters = fighterRepository.findAll();

        for (Fighter fighter : allFighters) {
            updateFighterRankingsAfterFight(fighter);
        }
    }

    public void updateFighterRankingsAfterFight(Fighter fighter) {
        Rankings rankings = rankingsRepository.findByFighter(fighter);
        if (rankings == null) {
            rankings = new Rankings();
            rankings.setFighter(fighter);
        }
        LocalDate currentDate = LocalDate.now();
        Date sqlDate = Date.valueOf(currentDate);
        rankings.setLastUpdated(sqlDate);
        rankings.setYear(currentDate.getYear());
        rankings.setWeightClass(fighter.getWeightClass());

        FightOutcome fightOutcome = getFightOutcomeForFighter(fighter);

        if (FightOutcome.Win.equals(fightOutcome)) {
            int updatedTotalWins = rankings.getTotalWins() + 1;
            int updatedRecentWins = rankings.getRecentWins() + 1;
            int updatedPoints = rankings.getTotalPoints() + 2;
            rankings.setTotalWins(updatedTotalWins);
            rankings.setRecentWins(updatedRecentWins);
            rankings.setTotalPoints(updatedPoints);
        } else if (FightOutcome.Knockouts.equals(fightOutcome)) {
            int updatedTotaKnockouts = rankings.getTotalKnockouts() + 1;
            int updatedRecentWins = rankings.getRecentWins() + 1;
            int updatedPoints = rankings.getTotalPoints() + 3;
            rankings.setTotalWins(updatedTotaKnockouts);
            rankings.setRecentWins(updatedRecentWins);
            rankings.setTotalPoints(updatedPoints);

        } else if (FightOutcome.Loss.equals(fightOutcome)) {
            int updatedRecent=  rankings.getTotalLoss() +1;
            rankings.setTotalLoss(updatedRecent);
            rankings.setTotalPoints(rankings.getTotalPoints());
        } else if (FightOutcome.Draw.equals(fightOutcome)) {
            int updatedTotalDraws = rankings.getTotalDraws() + 1;
            int updatedPoints = rankings.getTotalPoints() + 1;
            rankings.setTotalDraws(updatedTotalDraws);
            rankings.setTotalPoints(updatedPoints);
        } else if (FightOutcome.NotStarted.equals(fightOutcome)) {
            rankings.setTotalPoints(rankings.getTotalPoints());
        }

        rankingsRepository.save(rankings);
    }

    public FightOutcome getFightOutcomeForFighter(Fighter fighter) {
        if (fighter == null) {
            throw new IllegalArgumentException("Fighter not found.");
        }

        List<Fight> allFights = fighteRepository.findByFighter(fighter);

        FightOutcome overallOutcome = FightOutcome.NotStarted;

        for (Fight fight : allFights) {
            FightOutcome fightOutcome = fight.getFightOutcome();
            if (fightOutcome == FightOutcome.Win) {
                overallOutcome = FightOutcome.Win;
                break;
            } else if (fightOutcome == FightOutcome.Loss) {
                overallOutcome = FightOutcome.Loss;
                break;
            } else if (fightOutcome == FightOutcome.Draw) {
                overallOutcome = FightOutcome.Draw;
                break;
            }
        }

        return overallOutcome;
    }

}
