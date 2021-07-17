package br.com.brainweb.interview.core.features.hero.adapter.dto;

import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.Race;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class HeroQuery {

    private UUID id;

    private String name;

    private Race race;

    private PowerStatsQuery powerStats;

    private boolean enabled;

    private LocalDateTime createdDt;

    private LocalDateTime updatedDt;

}
