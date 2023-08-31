package com.tech.mma.dto;

import com.tech.mma.model.Event;
import com.tech.mma.model.FightOutcome;
import com.tech.mma.model.Fighter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FightDto {
    private String fighterNickName;
    private String event;
    private String opponentNickName;
    private int round;
    private Time timing;
    private FightOutcome fightOutcome;
}

