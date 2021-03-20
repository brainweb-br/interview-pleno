package br.com.brainweb.interview.core.features.hero;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brainweb.interview.core.features.hero.repository.HeroRepository;
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
	
	public Optional<Hero> findById(String id) {
		return heroRepository.findById(UUID.fromString(id));
	}
	
	public List<Hero> findByName(String name) {
		return heroRepository.findByName(name);
	}
	
	public void delete(String id) {
		heroRepository.delete(id);
	}
}
