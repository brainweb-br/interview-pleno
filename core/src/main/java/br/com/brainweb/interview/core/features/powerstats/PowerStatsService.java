package br.com.brainweb.interview.core.features.powerstats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.brainweb.interview.model.PowerStats;

@Service("powerStatsService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
public class PowerStatsService {
	
	@Autowired
	private PowerStatsRepository powerStatsRepository;

	@Transactional
	public PowerStats savePowerStats(PowerStats power) {
		return powerStatsRepository.saveAndFlush(power);
	}
	
	@Transactional
	public void deletePowerStats(PowerStats power) {
		powerStatsRepository.delete(power);
	}
	
}