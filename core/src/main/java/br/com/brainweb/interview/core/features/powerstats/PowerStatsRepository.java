package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.model.PowerStats;

import java.util.Optional;

public interface PowerStatsRepository {

    void create(PowerStats powerStats);

    void update(PowerStats powerStats);

    void delete(PowerStats powerStats);

    void deleteAll();

}
