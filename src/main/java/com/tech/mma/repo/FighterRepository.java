package com.tech.mma.repo;

import com.tech.mma.model.Fighter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FighterRepository extends JpaRepository<Fighter,Long>
{
    Fighter findByNickName(String nickName);

}
