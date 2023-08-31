package com.tech.mma.dto;

import com.tech.mma.model.Fighter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FighterResponseDTO {
    private Date fightDate;
    private String location;
    private Long  opponent;
    private Time timing;
}
