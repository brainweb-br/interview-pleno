package br.com.brainweb.interview.core.features.hero.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

	public Optional<Hero> findById(UUID id) {
		return heroRepository.findById(id);
	}

	public List<Hero> findByName(String name) {
		return heroRepository.findByNameIgnoreCase(name);
	}

	public Hero saveHero(@RequestBody Hero hero) {
		PowerStats p = powerRepository.save(hero.getPower());
		hero.setPower(p);
		return heroRepository.save(hero);
	}
	
	public Hero editHero(@RequestBody Hero hero) {
		hero.getPower().setUpdatedAt(new Date());
		PowerStats p = powerRepository.save(hero.getPower());
		hero.setUpdatedAt(new Date());
		hero.setPower(p);
		return heroRepository.save(hero);
	}

	public void deleteHero(@RequestBody Hero hero) {
		heroRepository.delete(hero);
		powerRepository.delete(hero.getPower());
	}
}
