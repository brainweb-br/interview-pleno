package br.com.brainweb.interview.core.features.hero.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.brainweb.interview.core.features.hero.repository.HeroRepository;
import br.com.brainweb.interview.core.features.hero.repository.PowerStatsRepository;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.bean.CompareHero;

@ExtendWith(MockitoExtension.class)
public class HeroServiceTest {

	@Mock
	private HeroRepository heroRepository;

	@Mock
	private PowerStatsRepository powerRepository;

	@InjectMocks
	private HeroService heroService;

	@Test
	public void testFindById() throws Exception {
		Hero h = createHero();
		Optional<Hero> optHero = Optional.of(h);
		when(heroRepository.findById(h.getId())).thenReturn(optHero);
		assertNotNull(heroService.findById(h.getId()), "Objeto nao e nulo");
	}

	@Test
	public void testFindByName() throws Exception {
		Hero h = createHero();
		List<Hero> listHero = new ArrayList<Hero>();
		when(heroRepository.findByNameIgnoreCase(h.getName())).thenReturn(listHero);
		assertNotNull(heroService.findByName(h.getName()), "Objeto nao e nulo");
	}

	@Test
	public void testSaveHero() throws Exception {
		Hero h = createHero();
		when(powerRepository.save(h.getPower())).thenReturn(h.getPower());
		when(heroRepository.save(h)).thenReturn(h);
		assertNotNull(heroService.saveHero(h), "Objeto nao e nulo");
	}

	@Test
	public void testEditHero() throws Exception {
		Hero h = createHero();
		when(powerRepository.save(h.getPower())).thenReturn(h.getPower());
		when(heroRepository.save(h)).thenReturn(h);
		assertNotNull(heroService.editHero(h), "Objeto nao e nulo");
	}

	@Test
	public void testDeleteHero() throws Exception {
		Hero h = createHero();
		assertNotNull(heroService.deleteHero(h), "Objeto nao e nulo");
	}

	@Test
	public void testCompareHero() throws Exception {
		Hero h1 = createHero();
		Hero h2 = createHero();
		CompareHero compareHero = new CompareHero();
		compareHero.setHero1(h1.getId());
		compareHero.setHero2(h2.getId());

		assertNotNull(heroService.compareHero(compareHero, h1, h2), "Objeto nao e nulo");
	}

	public Hero createHero() {
		Hero hero = new Hero();
		PowerStats power = new PowerStats();

		power.setId(UUID.randomUUID());
		power.setStrength(1);
		power.setAgility(1);
		power.setDexterity(1);
		power.setIntelligence(1);
		power.prePersist();
		power.setCreatedAt(new Date());
		power.setUpdatedAt(new Date());

		hero.setId(UUID.randomUUID());
		hero.setName("Heroi 1");
		hero.setRace("HUMAN");
		hero.setEnabled(true);
		hero.prePersist();
		hero.setCreatedAt(new Date());
		hero.setUpdatedAt(new Date());
		hero.setPower(power);

		hero.toString();
		return hero;
	}

}
