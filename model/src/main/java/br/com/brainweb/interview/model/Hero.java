package br.com.brainweb.interview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Hero {

    private UUID id;

    private String name;

    private Race race;

    private PowerStats powerStats;

    private boolean enabled;

    private LocalDateTime createdDt;

    private LocalDateTime updatedDt;

    public Hero() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    public UUID getPowerStatsId() {
        return Optional.ofNullable(this.powerStats)
                .map(PowerStats::getId)
                .orElse(null);
    }

}
