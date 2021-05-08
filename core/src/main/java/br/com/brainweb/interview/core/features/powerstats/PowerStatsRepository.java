package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.model.PowerStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PowerStatsRepository extends JpaRepository<PowerStats, UUID> {
}
