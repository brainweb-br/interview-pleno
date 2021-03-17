package br.com.brainweb.interview.core.features.hero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;

@Service
public class HeroService {
	
	@Autowired
	private HeroRepository heroRepository;
	
	@Autowired
	private PowerStatsRepository powerStatsRepository;
	
	@Transactional
	public Hero save(Hero hero) {
		PowerStats powerStats = hero.getPowerStats();
		
		powerStatsRepository.save(powerStats);
		heroRepository.save(hero);
		return hero;
	}
}
