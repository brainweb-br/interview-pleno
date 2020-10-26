package br.com.brainweb.interview.core.features.powerstats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.brainweb.interview.core.features.powerstats.repository.PowerStatsRepository;
import br.com.brainweb.interview.model.PowerStats;

@Service
public class PowerStatsService {

	@Autowired
	private PowerStatsRepository powerRepository;
	
	public PowerStats findById(int id) {
		return powerRepository.findById(id);
	}
	
	public PowerStats savePowerStats(@RequestBody PowerStats powerStats) {
		return powerRepository.save(powerStats);
	}
}
