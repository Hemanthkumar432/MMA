package com.tech.mma.repo;


import com.tech.mma.model.Fighter;
import com.tech.mma.model.Rankings;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RankingsRepository extends JpaRepository<Rankings, Long> {
    Rankings findByFighter(Fighter fighter);
}
