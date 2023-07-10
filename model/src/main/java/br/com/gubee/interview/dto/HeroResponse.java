package br.com.gubee.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HeroResponse {

    private String name;
    private String race;
    private boolean enabled;
    private int strength;
    private int agility;
    private int dexterity;
    private int intelligence;

}
