package com.tech.mma.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Fight")
public class Fight
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fight_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fighter_id")
    private Fighter fighter;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "opponent_id")
    private Fighter opponent;

    @Column(name = "location")
    private String location;

    @Column(name = "fightOutcome", nullable = false)
    @Enumerated(EnumType.STRING)
    private FightOutcome fightOutcome;

    @Column(name = "fightDate")
    private Date fightDate;

    @Column(name = "round")
    private int round;

    @Column(name = "timing")
    private Time timing;
}

