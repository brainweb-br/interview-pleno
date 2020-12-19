package br.com.brainweb.interview.core.features.hero.model;

import br.com.brainweb.interview.enums.EnumRace;
import br.com.brainweb.interview.model.PowerStats;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class HeroInput {

    @NotBlank
    private String name;
    @NotNull
    private EnumRace race;
    @NotNull
    private Boolean enabled;
    @NotNull
    private PowerStats powerStatsId;
}
