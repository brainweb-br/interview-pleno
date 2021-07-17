package br.com.brainweb.interview.core.features.hero.adapter.dto;

import br.com.brainweb.interview.model.Race;
import lombok.Data;
import lombok.NonNull;

@Data
public class HeroCommand {

    @NonNull
    private String name;

    @NonNull
    private Race race;

    @NonNull
    private PowerStatsCommand powerStats;

    private boolean enabled;
}
