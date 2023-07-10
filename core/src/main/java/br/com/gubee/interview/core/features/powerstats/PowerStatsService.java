package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.model.PowerStats;

import java.util.UUID;

public interface PowerStatsService {

    PowerStats getById(UUID id);
    PowerStats save(PowerStats powerStats);
    PowerStats deleteById(UUID id);

}
