package br.com.brainweb.interview.core.features.powerstats;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brainweb.interview.model.PowerStats;

public interface PowerStatsRepository extends JpaRepository<PowerStats, UUID>{
	
	PowerStats create(PowerStats powerStats);
}
