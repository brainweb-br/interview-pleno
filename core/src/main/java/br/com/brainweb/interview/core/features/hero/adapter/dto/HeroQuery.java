package br.com.brainweb.interview.core.features.hero.adapter.dto;

import br.com.brainweb.interview.model.Race;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@Value
public class HeroQuery {

    UUID id;

    String name;

    Race race;

    PowerStatsQuery powerStats;

    boolean enabled;

    String createdDt;

    String updatedDt;

}
