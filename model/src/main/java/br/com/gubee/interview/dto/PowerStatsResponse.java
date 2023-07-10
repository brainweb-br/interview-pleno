package br.com.gubee.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PowerStatsResponse {

    private int strength;
    private int agility;
    private int dexterity;
    private int intelligence;
}
