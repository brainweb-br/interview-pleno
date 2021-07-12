package br.com.brainweb.interview.core.features.hero.dto;

import br.com.brainweb.interview.model.Race;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeroDTO {

    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private Race race;
    private UUID powerStatsId;
    @NotNull
    private short strength;
    @NotNull
    private short agility;
    @NotNull
    private short dexterity;
    @NotNull
    private short intelligence;
    @NotNull
    private boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
