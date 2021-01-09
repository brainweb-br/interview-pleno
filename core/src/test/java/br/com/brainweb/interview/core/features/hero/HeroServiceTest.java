package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.repository.HeroRepository;
import br.com.brainweb.interview.core.repository.PowerStatsRepository;
import br.com.brainweb.interview.model.CompareHero;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HeroServiceTest {

	@InjectMocks
	private HeroService heroService;

	@Mock
	private HeroRepository heroRepository;

	@Mock
	private PowerStatsRepository powerStatsRepository;

	@Test
	public void testFindById() {

		Hero hero = createHero();

		when(heroRepository.findById(any())).thenReturn(Optional.of(hero));

		Optional<Hero> response = heroService.findById(hero.getId());

		assertThat(response.isPresent());

	}

	@Test
	public void testSaveHero() {

		Hero hero = createHero();

		when(powerStatsRepository.save(any())).thenReturn(hero.getPower());
		when(heroRepository.save(any())).thenReturn(hero);

		Hero response = heroService.saveHero(hero);

		assertNotNull(response);
	}

	@Test
	public void testFindByName() {

		Hero hero = createHero();

		when(heroRepository.findByNameIgnoreCase(any())).thenReturn(Arrays.asList(hero));

		List<Hero> response = heroService.findByName(hero.getName());

		assertEquals(1,response.size());

	}
	@Test
	public void testFindByIds() {

		Hero hero = createHero();

		when(heroRepository.findByIds(any())).thenReturn(Arrays.asList(hero));

		List<Hero> response = heroService.findByIds(Arrays.asList(hero.getId()));

		assertEquals(1,response.size());

	}

	@Test
	public void testEditHero() {

		Hero hero = createHero();

		when(powerStatsRepository.save(any())).thenReturn(hero.getPower());
		when(heroRepository.save(any())).thenReturn(hero);

		Hero response = heroService.editHero(hero);
		assertNotNull(response);
	}

	@Test
	public void testCompareHero() {

		Hero hero = createHero();
		Hero hero2 = createHero();

		when(heroRepository.findById(any())).thenReturn(Optional.of(hero));
		when(heroRepository.findById(any())).thenReturn(Optional.of(hero2));

		CompareHero response = heroService.compareHeroes(hero.getId(),hero2.getId());
		assertNotNull(response);
		assertEquals("SAME POWER",response.getName());
	}


	@Test
	public void testDeleteHero() {

		Hero hero = createHero();

		heroService.deleteHero(hero);
		verify(heroRepository, times(1)).delete(hero);
		verify(powerStatsRepository, times(1)).delete(hero.getPower());

	}



	public Hero createHero() {
		Hero hero = new Hero();
		PowerStats power = new PowerStats();
		power.setId(UUID.randomUUID());
		power.setStrength(1);
		power.setAgility(1);
		power.setDexterity(1);
		power.setIntelligence(1);
		power.setCreatedAt(new Date());
		power.setUpdatedAt(new Date());

		hero.setId(UUID.randomUUID());
		hero.setName("Heroi 1");
		hero.setRace("HUMAN");
		hero.setEnabled(true);
		hero.setCreatedAt(new Date());
		hero.setUpdatedAt(new Date());
		hero.setPower(power);

		hero.toString();
		return hero;
	}

}
