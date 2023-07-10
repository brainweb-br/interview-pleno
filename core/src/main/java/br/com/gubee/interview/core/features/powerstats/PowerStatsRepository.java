package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.dto.HeroResponse;
import br.com.gubee.interview.dto.PowerStatsResponse;
import br.com.gubee.interview.model.Hero;

import java.util.UUID;

public interface PowerStatsRepository {

    UUID save(PowerStatsResponse powerStatsResponse);
    int deleteById(UUID id);
    int editHero(HeroResponse hero, UUID id);
}
