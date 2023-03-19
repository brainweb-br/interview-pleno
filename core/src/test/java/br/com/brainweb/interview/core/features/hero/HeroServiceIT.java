package br.com.brainweb.interview.core.features.hero;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.enums.Race;

@SpringBootTest
@ActiveProfiles("it")
public class HeroServiceIT {
	
	@Autowired
	private HeroService heroService;
	
	@Autowired
	private HeroRepository heroRepository;
		
	@Test
	public void testWhenHasDuplicateName() throws Exception {
		Hero hero = createHero();
		Mockito.when(heroRepository.findByName(hero.getName())).thenReturn(hero);
		
		Hero hero2 = createHero();
		assertThatExceptionOfType(Exception.class).isThrownBy(() -> heroService.create(hero2));
	}
	
	private Hero createHero() {
		return Hero.builder()
				.name("Batman")
				.race(Race.HUMAN)
				.powerStats(createPowerStats())
				.build();
	}

	private PowerStats createPowerStats() {
		return PowerStats.builder()
				.agility(4)
				.dexterity(8)
	            .strength(6)
	            .intelligence(10)
	            .build();
	}
}
