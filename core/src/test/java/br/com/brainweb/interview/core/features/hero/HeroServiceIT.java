package br.com.brainweb.interview.core.features.hero;

import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.Race;

@ActiveProfiles("it")
@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class HeroServiceIT {
	
	@Autowired
	private HeroRepository heroRepository;
	
	@Autowired
	private PowerStatsRepository powerStatsRepository;
	
	@Test
	public void saveHero() {
		
		PowerStats power = criacaoPowerStats();
		this.powerStatsRepository.saveAndFlush(power);
		
		Hero hero = criacaoHero();
		hero.setPowerStats(power);
		
		this.heroRepository.saveAndFlush(hero);
		Assertions.assertThat(hero.getId()).isNotNull();		
	}
	
	@Test
    public void deleteHero() {
		PowerStats power = criacaoPowerStats();
		this.powerStatsRepository.saveAndFlush(power);
		
		Hero hero = criacaoHero();
		hero.setPowerStats(power);
		
		this.heroRepository.saveAndFlush(hero);
		this.heroRepository.delete(hero);
		
		List<Hero> heroes = heroRepository.findAll();
		Assertions.assertThat(heroes).size().isLessThan(1);
    }
	
	@Test
    public void updateHero() {
		PowerStats power = criacaoPowerStats();
		this.powerStatsRepository.saveAndFlush(power);
		
		Hero hero = criacaoHero();
		hero.setPowerStats(power);
		
		this.heroRepository.saveAndFlush(hero);
		
		hero.setName("Big Test");
		this.heroRepository.saveAndFlush(hero);

		Assertions.assertThat(hero.getName()).isEqualTo("Big Test");
    }
	
	private Hero criacaoHero() {
		Hero hero = new Hero();
		hero.setId(null);
		hero.setName("Flash");
		hero.setRace(Race.ALIEN);
		hero.setEnabled(true);
		hero.setCreatedAt(LocalDateTime.now());
		hero.setUpdatedAt(LocalDateTime.now());

		return hero;
	}
	
	private PowerStats criacaoPowerStats() {
		PowerStats powerStats = new PowerStats();
		powerStats.setId(null);
		powerStats.setDexterity((short) 10);
		powerStats.setAgility((short) 8);
		powerStats.setCreatedAt(LocalDateTime.now());
		powerStats.setIntelligence((short) 5);
		powerStats.setStrength((short) 3);
		powerStats.setUpdatedAt(LocalDateTime.now());
		
		return powerStats;
	}
	
}
