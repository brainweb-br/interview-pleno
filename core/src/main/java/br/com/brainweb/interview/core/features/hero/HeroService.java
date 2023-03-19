package br.com.brainweb.interview.core.features.hero;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsService;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;

@Service
public class HeroService {
	
	@Autowired
	private HeroRepository heroRepository;
	
	@Autowired
	private PowerStatsService powerStatsService;
	
	@Transactional
	public Hero create(Hero hero) {
		PowerStats powerStats = powerStatsService.create(new PowerStats(hero));
		hero.setPowerStats(powerStats);
		
		return heroRepository.save(hero);		
	}

	public Hero findById(UUID id) {
		return heroRepository.findById(id).orElseThrow();
	}

	public List<Hero> findByName(String name) {
		return heroRepository.findAll(Sort.by(Sort.Direction.ASC, name));
	}

	public Hero update(Hero hero) {
		return heroRepository.save(hero);
	}

	public void delete(Hero hero) {
		heroRepository.delete(hero);
	}
}
