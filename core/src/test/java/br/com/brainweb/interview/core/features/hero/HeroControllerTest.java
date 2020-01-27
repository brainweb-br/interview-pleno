package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.exception.DataValidationException;
import br.com.brainweb.interview.core.features.exception.NotFoundException;
import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.dto.CompareHeroDTO;
import br.com.brainweb.interview.dto.HeroDTO;
import br.com.brainweb.interview.dto.PowerStatsDTO;
import br.com.brainweb.interview.enums.Race;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Random;
import java.util.UUID;

@WebAppConfiguration
@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HeroControllerTest {

	@Autowired
	private HeroRepository heroRepository;

	@Autowired
	private HeroService heroService;

	@Autowired
	private PowerStatsRepository powerStatsRepository;

	private static String name1 = "TestHero1";
	private static String name2 = "TestHero2";


	@Test(expected = DataValidationException.class)
	public void should1ThrowDataValidationExceptionForStats() throws Exception {
		HeroDTO heroDTO = new HeroDTO();
		heroDTO.setName(name1);
		heroDTO.setRace(Race.CYBORG.getDescription());
		heroService.create(heroDTO);
	}

	@Test
	public void should2CreateHero() throws Exception {
		HeroDTO heroDTO = new HeroDTO();
		heroDTO.setName(name1);
		heroDTO.setRace(Race.CYBORG.getDescription());
		PowerStatsDTO powerStats = new PowerStatsDTO(6, 6, 6, 6);
		heroDTO.setPowerStats(powerStats);
		Hero hero = heroService.create(heroDTO);

		assert hero.getId() != null;
	}

	@Test
	public void should2CreateHero2() throws Exception {
		HeroDTO heroDTO = new HeroDTO();
		heroDTO.setName(name2);
		heroDTO.setRace(Race.CYBORG.getDescription());
		PowerStatsDTO powerStats = new PowerStatsDTO(6, 6, 6, 6);
		heroDTO.setPowerStats(powerStats);
		Hero hero = heroService.create(heroDTO);

		assert hero.getId() != null;
	}

	@Test
	public void should3UpdateHero() throws Exception {
		Hero hero = heroRepository.findByName(name1).orElseThrow(NotFoundException::new);
		HeroDTO heroDTO = new HeroDTO();
		heroDTO.setName(name1 + "_alterado");
		heroDTO.setRace(Race.ALIEN.getDescription());
		heroDTO.setEnabled(true);
		PowerStatsDTO powerStats = new PowerStatsDTO(6, 6, 6, 6);
		heroDTO.setPowerStats(powerStats);
		hero = heroService.update(hero, heroDTO);

		assert hero.getRace().equals(Race.ALIEN.getDescription()) && hero.getName().equals(heroDTO.getName());
	}

	@Test
	public void should4UpdateHero() throws Exception {
		Hero hero = heroRepository.findByName(name1 + "_alterado").orElseThrow(NotFoundException::new);
		HeroDTO heroDTO = new HeroDTO();
		heroDTO.setName(name1);
		heroDTO.setRace(Race.HUMAN.getDescription());
		heroDTO.setEnabled(true);
		PowerStatsDTO powerStats = new PowerStatsDTO(6, 6, 6, 6);
		heroDTO.setPowerStats(powerStats);
		hero = heroService.update(hero, heroDTO);

		assert hero.getRace().equals(Race.HUMAN.getDescription()) && hero.getName().equals(heroDTO.getName());
	}


	@Test
	public void should5FindHeroByIdAndName() throws Exception {
		Hero heroName = heroRepository.findByName(name1).orElseThrow(NotFoundException::new);
		Hero heroId = heroRepository.findById(heroName.getId()).orElseThrow(NotFoundException::new);
		assert heroName.getId().equals(heroId.getId()) && heroName.getName().equals(name1);
	}

	@Test
	public void should6CompareHeros() throws Exception {
		Hero hero1 = heroRepository.findByName(name1).orElseThrow(NotFoundException::new);
		Hero hero2 = heroRepository.findByName(name2).orElseThrow(NotFoundException::new);
		CompareHeroDTO compareHeroDTO = heroService.compareHeros(hero1, hero2);
		PowerStats powerStats1 = hero1.getPowerStats();
		PowerStats powerStats2 = hero2.getPowerStats();
		assert (powerStats1.getIntelligence() - powerStats2.getIntelligence()) == compareHeroDTO.getStatsDiference().getIntelligence();
		assert (powerStats1.getStrength() - powerStats2.getStrength()) == compareHeroDTO.getStatsDiference().getStrength();
		assert (powerStats1.getDexterity() - powerStats2.getDexterity()) == compareHeroDTO.getStatsDiference().getDexterity();
		assert (powerStats1.getAgility() - powerStats2.getAgility()) == compareHeroDTO.getStatsDiference().getAgility();
	}

	@Test
	public void should6RemoveHero() throws Exception {
		Hero hero = heroRepository.findByName(name1).orElseThrow(NotFoundException::new);
		heroRepository.delete(hero);
		powerStatsRepository.delete(hero.getPowerStats());
		hero = heroRepository.findById(hero.getId()).orElse(null);
		assert hero == null;
	}

	@Test
	public void should6RemoveHero2() throws Exception {
		Hero hero = heroRepository.findByName(name2).orElseThrow(NotFoundException::new);
		heroRepository.delete(hero);
		powerStatsRepository.delete(hero.getPowerStats());
		hero = heroRepository.findById(hero.getId()).orElse(null);
		assert hero == null;
	}


}
