package com.tech.mma.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Rankings")
public class Rankings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ranking_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fighter_id")
    private Fighter fighter;

    @Column(name = "weightClass")
    private String weightClass;

    @Column(name = "rankNum")
    private int rankNum;

    @Column(name = "totalWins")
    private int totalWins;

    @Column(name = "recentWins")
    private int recentWins;

    @Column(name = "totalLoss")
    private int totalLoss;

    @Column(name = "totalDraws")
    private int totalDraws;

    @Column(name = "totalKnockouts", columnDefinition = "INT default 0")
    private int totalKnockouts;

    @Column(name = "totalPoints")
    private int totalPoints;

    @Column(name = "lastUpdated")
    private Date lastUpdated;

    @Column(name = "year")
    private int year;



}
