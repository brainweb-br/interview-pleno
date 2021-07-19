package br.com.brainweb.interview.core.features.hero.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PowerStatsCommand {

    int strength;

    int agility;

    int dexterity;

    int intelligence;

}
