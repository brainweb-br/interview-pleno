package br.com.brainweb.interview.core.features.hero.adapter.dto;

import br.com.brainweb.interview.model.Race;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;

@Data
public class HeroCommand {

    @NonNull
    @NotEmpty
    private String name;

    @NonNull
    private Race race;

    @NonNull
    private PowerStatsCommand powerStats;

    private boolean enabled;
}
