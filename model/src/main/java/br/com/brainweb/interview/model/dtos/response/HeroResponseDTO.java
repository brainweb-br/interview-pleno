package br.com.brainweb.interview.model.dtos.response;

import br.com.brainweb.interview.model.enums.Race;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "race", "powerStats", "enabled", "created", "updated"})
public class HeroResponseDTO extends ResponseDTO {


    @Builder
    public HeroResponseDTO(UUID id, String name, Race race, PowerStatsResponseDTO powerStats,
                           Boolean enabled, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.race = race;
        this.powerStats = powerStats;
        this.enabled = enabled;
    }


    private String name;

    private Race race;

    private PowerStatsResponseDTO powerStats;

    private Boolean enabled;

}
