package com.tech.mma.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FighterDto
{
    private String fullName;
    private String nickName;
    private int age;
    private String weightClass;
    private double weight;
    private double height;
    private String nationality;
    private String teamAffiliation;

}
