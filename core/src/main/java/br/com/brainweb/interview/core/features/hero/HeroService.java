package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.exception.DataValidationException;
import br.com.brainweb.interview.dto.CompareHeroDTO;
import br.com.brainweb.interview.enums.Race;
import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.dto.HeroDTO;
import br.com.brainweb.interview.dto.PowerStatsDTO;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class HeroService {
	@Autowired
	private HeroRepository heroRepository;

	@Autowired
	private PowerStatsRepository powerStatsRepository;


	public Boolean verifyRaceOfHero(String raceName) {
		return Race.ACCEPT_RACES.stream().anyMatch(race -> race.getDescription().equals(raceName));
	}

	public void verifyHero(HeroDTO heroDTO) {
		StringBuilder msgErro = new StringBuilder("");
		if (heroRepository.existsByName(heroDTO.getName()).orElse(false)) {
			msgErro.append("Hero already registered! \n");
		}
		if (heroDTO.getName().equals("") || heroDTO.getName() == null) {
			msgErro.append("Name not found! \n");
		}
		if (!verifyRaceOfHero(heroDTO.getRace())) {
			msgErro.append("Race not found! \n");
		}
		if (heroDTO.getPowerStats() != null) {
			PowerStatsDTO powerStatsDTO = heroDTO.getPowerStats();
			if (powerStatsDTO.getAgility() == null || powerStatsDTO.getDexterity() == null || powerStatsDTO.getIntelligence() == null || powerStatsDTO.getStrength() == null) {
				msgErro.append("Power Stats not found! \n");
			}
		} else {
			msgErro.append("Power Stats not found! \n");
		}
		if (!msgErro.toString().equals("")) {
			throw new DataValidationException(msgErro.toString());
		}
	}

	public Hero create(HeroDTO heroDTO) {

		verifyHero(heroDTO);
		PowerStats powerStats = new PowerStats();
		parsePowerStats(powerStats, heroDTO.getPowerStats());
		powerStatsRepository.save(powerStats);


		Hero hero = new Hero();
		parseHero(hero, heroDTO);
		hero.setPowerStats(powerStats);
		hero.setEnabled(true);

		return heroRepository.saveAndFlush(hero);
	}

	public Hero update(Hero hero, HeroDTO heroDTO) {

		verifyHero(heroDTO);
		PowerStats powerStats = hero.getPowerStats();
		parsePowerStats(powerStats, heroDTO.getPowerStats());
		powerStatsRepository.save(powerStats);

		parseHero(hero, heroDTO);
		hero.setPowerStats(powerStats);
		hero.setEnabled(heroDTO.getEnabled());

		return heroRepository.saveAndFlush(hero);
	}

	public CompareHeroDTO compareHeros(Hero hero1, Hero hero2){
		PowerStatsDTO diferenceStats = hero1.getPowerStats().diferenceStats(hero2.getPowerStats());
		CompareHeroDTO compareHeroDTO = new CompareHeroDTO();
		compareHeroDTO.setFirstHero(hero1.getId());
		compareHeroDTO.setSecondHero(hero2.getId());
		compareHeroDTO.setStatsDiference(diferenceStats);
		return compareHeroDTO;
	}

	public void parsePowerStats(PowerStats powerStats, PowerStatsDTO powerStatsDTO) {
		powerStats.setAgility(powerStatsDTO.getAgility());
		powerStats.setDexterity(powerStatsDTO.getDexterity());
		powerStats.setIntelligence(powerStatsDTO.getIntelligence());
		powerStats.setStrength(powerStatsDTO.getStrength());
	}

	public void parseHero(Hero hero, HeroDTO heroDTO) {
		Race race = Race.valueOf(heroDTO.getRace());
		hero.setName(heroDTO.getName());
		hero.setRace(race.getDescription());

	}
}
