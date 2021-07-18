package br.com.brainweb.interview.core.features.hero.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Value
public class PowerStatsQuery {

    UUID id;

    int strength;

    int agility;

    int dexterity;

    int intelligence;

    String createdDt;

    String updatedDt;

}
