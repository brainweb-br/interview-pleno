package br.com.brainweb.interview.core.features.hero.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brainweb.interview.core.features.hero.repository.HeroRepository;
import br.com.brainweb.interview.core.features.hero.repository.PowerStatsRepository;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.bean.CompareHero;

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

	public Hero saveHero(Hero hero) {
		PowerStats p = powerRepository.save(hero.getPower());
		hero.setPower(p);
		return heroRepository.save(hero);
	}
	
	public Hero editHero(Hero hero) {
		hero.getPower().setUpdatedAt(new Date());
		PowerStats p = powerRepository.save(hero.getPower());
		hero.setUpdatedAt(new Date());
		hero.setPower(p);
		return heroRepository.save(hero);
	}

	public String deleteHero(Hero hero) {
		heroRepository.delete(hero);
		powerRepository.delete(hero.getPower());
		String msg = "Heroi deletado com sucesso - id: "+hero.getId();
		return msg;
	}

	public CompareHero compareHero(CompareHero compareHero, Hero hero, Hero hero2) {
		PowerStats p = hero.getPower();
		PowerStats p2 = hero2.getPower();
		compareHero.setStrength(p.getStrength()-p2.getStrength());
		compareHero.setAgility(p.getAgility()-p2.getAgility());
		compareHero.setDexterity(p.getDexterity()-p2.getDexterity());
		compareHero.setIntelligence(p.getIntelligence()-p2.getIntelligence());
		
		return compareHero;
	}
}
