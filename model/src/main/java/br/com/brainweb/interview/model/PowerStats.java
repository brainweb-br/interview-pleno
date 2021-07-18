package br.com.brainweb.interview.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class PowerStats {

    UUID id;

    int strength;

    int agility;

    int dexterity;

    int intelligence;

    LocalDateTime createdDt;

    LocalDateTime updatedDt;

    public PowerStats(int strength,
                      int agility,
                      int dexterity,
                      int intelligence) {
        this.id = UUID.randomUUID();
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.createdDt = LocalDateTime.now();
        this.updatedDt = LocalDateTime.now();
    }

    public PowerStats copy(PowerStats powerStats) {
        return this.toBuilder()
                .strength(powerStats.getStrength())
                .agility(powerStats.getAgility())
                .dexterity(powerStats.getDexterity())
                .intelligence(powerStats.getIntelligence())
                .updatedDt(powerStats.getUpdatedDt())
                .build();
    }
}
