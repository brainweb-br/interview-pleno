package br.com.brainweb.interview.core.features.powerstats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brainweb.interview.model.PowerStats;

@Service
public class PowerStatsService {
	
	@Autowired
	private PowerStatsRepository powerStatsRepository;
	
	public PowerStats create (PowerStats powerStats) {
		return powerStatsRepository.create(powerStats);
	}
}
