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
@Table(name = "Fighter")
public class Fighter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fighter_id")
    private Long id;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "nickName", nullable = false)
    private String nickName;

    @Column(name = "age")
    private int age;

    @Column(name = "weightClass")
    private String weightClass;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "teamAffiliation", columnDefinition = "VARCHAR(100) DEFAULT 'MMA FACTORY'")
    private String teamAffiliation;

    @Column(name = "weight", precision = 5, scale = 2)
    private double weight;

    @Column(name = "height", precision = 5, scale = 2)
    private double height;

    @Column(name = "LastFight")
    private Date lastFight;

    @Column(name = "totalFights", columnDefinition = "INT default 0")
    private int totalFights;

    @Column(name = "totalWins", columnDefinition = "INT default 0")
    private int totalWins;

    @Column(name = "totalLosses", columnDefinition = "INT default 0")
    private int totalLosses;

    @Column(name = "totalKnockouts", columnDefinition = "INT default 0")
    private int totalKnockouts;

    @Column(name = "totalSubmissions", columnDefinition = "INT default 0")
    private int totalSubmissions;

}

