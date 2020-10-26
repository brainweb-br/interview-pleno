package br.com.brainweb.interview.core.features.hero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.brainweb.interview.core.features.hero.repository.HeroRepository;
import br.com.brainweb.interview.core.features.powerstats.repository.PowerStatsRepository;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;

@Service
public class HeroService {

	@Autowired
	private HeroRepository heroRepository;
	
	@Autowired
	private PowerStatsRepository powerRepository;

	public Hero findById(int id) {
		return heroRepository.findById(id);
	}
	
	public Hero findByName(String name) {
		return heroRepository.findByName(name);
	}

	public Hero saveHero(@RequestBody Hero hero) {
		PowerStats p = powerRepository.save(hero.getPowerStats());
		hero.setPowerStats(p);
		return heroRepository.save(hero);
	}
}
