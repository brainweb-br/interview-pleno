package br.com.brainweb.interview.core.features.hero.adapter.dto;

import br.com.brainweb.interview.model.Race;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeroQuery {

    private UUID id;

    private String name;

    private Race race;

    private PowerStatsQuery powerStats;

    private boolean enabled;

    private String createdDt;

    private String updatedDt;

}
