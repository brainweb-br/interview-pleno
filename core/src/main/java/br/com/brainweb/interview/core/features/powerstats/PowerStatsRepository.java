package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.model.PowerStats;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PowerStatsRepository extends CrudRepository<PowerStats, UUID> {
}
