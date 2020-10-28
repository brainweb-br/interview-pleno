package br.com.brainweb.interview.core.features.hero;

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

import br.com.brainweb.interview.core.features.hero.controller.HeroController;
import br.com.brainweb.interview.core.features.hero.service.HeroService;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;

@ExtendWith(MockitoExtension.class)
public class HeroControllerTest {

	@Mock
	private HeroService heroService;

	@InjectMocks
	private HeroController heroController;

	@Test
	public void testFindById() throws Exception {

		Optional<Hero> optHero = Optional.of(createHero());
		UUID id = UUID.fromString("85ef9d3f-7be2-4499-ad15-04a996f6554f");
		when(heroService.findById(id)).thenReturn(optHero);
		assertNotNull(heroController.findById(id), "Mensagem não e nula");

		Optional<Hero> optHeroEmpty = Optional.empty();
		UUID id2 = UUID.fromString("85ef9d3f-7be2-4499-ad15-04a996f6554d");
		when(heroService.findById(id2)).thenReturn(optHeroEmpty);
		assertNotNull(heroController.findById(id2), "Mensagem não e nula");

		UUID id3 = UUID.fromString("85ef9d3f-7be2-4499-ad15-04a996f6554e");
		when(heroService.findById(id3)).thenThrow(NullPointerException.class);
		assertNotNull(heroController.findById(id3), "Mensagem não e nula");
	}

	@Test
	public void testFindByName() throws Exception {

		List<Hero> listHero = new ArrayList<Hero>();

		String name = "teste";
		when(heroService.findByName(name)).thenReturn(listHero);
		assertNotNull(heroController.findByName(name), "Mensagem não e nula");

		String name2 = "exce";
		when(heroService.findByName(name2)).thenThrow(NullPointerException.class);
		assertNotNull(heroController.findByName(name2), "Mensagem não e nula");
	}

	@Test
	public void testSave() throws Exception {
		Hero h = createHero();
		when(heroService.saveHero(h)).thenReturn(h);
		assertNotNull(heroController.saveHero(h), "Mensagem não e nula");

		List<Hero> listHero = new ArrayList<Hero>();
		listHero.add(h);
		String name = "Heroi 1";
		when(heroService.findByName(name)).thenReturn(listHero);
		assertNotNull(heroController.saveHero(h), "Mensagem não e nula");

		Hero h2 = createHero();
		when(heroService.saveHero(h2)).thenThrow(NullPointerException.class);
		assertNotNull(heroController.saveHero(h2), "Mensagem não e nula");

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
