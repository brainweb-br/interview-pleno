package br.com.brainweb.interview.core.features.hero.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class PowerStatsQuery {

    UUID id;

    int strength;

    int agility;

    int dexterity;

    int intelligence;

    LocalDateTime createdDt;

    LocalDateTime updatedDt;

}
