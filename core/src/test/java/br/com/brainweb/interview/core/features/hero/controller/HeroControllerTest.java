package br.com.brainweb.interview.core.features.hero.controller;

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

import br.com.brainweb.interview.core.features.hero.service.HeroService;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.bean.CompareHero;

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
		assertNotNull(heroController.findById(id), "Objeto nao e nulo");
	}

	@Test
	public void testFindByIdNotFound() throws Exception {
		Optional<Hero> optHeroEmpty = Optional.empty();
		UUID id2 = UUID.fromString("85ef9d3f-7be2-4499-ad15-04a996f6554d");
		when(heroService.findById(id2)).thenReturn(optHeroEmpty);
		assertNotNull(heroController.findById(id2), "Objeto nao e nulo");
	}

	@Test
	public void testFindByIdException() throws Exception {
		UUID id3 = UUID.fromString("85ef9d3f-7be2-4499-ad15-04a996f6554e");
		when(heroService.findById(id3)).thenThrow(NullPointerException.class);
		assertNotNull(heroController.findById(id3), "Objeto nao e nulo");
	}

	@Test
	public void testFindByName() throws Exception {
		List<Hero> listHero = new ArrayList<Hero>();
		String name = "teste";
		when(heroService.findByName(name)).thenReturn(listHero);
		assertNotNull(heroController.findByName(name), "Objeto nao e nulo");
	}

	@Test
	public void testFindByNameException() throws Exception {
		String name = "exce";
		when(heroService.findByName(name)).thenThrow(NullPointerException.class);
		assertNotNull(heroController.findByName(name), "Objeto nao e nulo");
	}

	@Test
	public void testSaveException() throws Exception {
		Hero h = createHero();
		when(heroService.saveHero(h)).thenThrow(NullPointerException.class);
		assertNotNull(heroController.saveHero(h), "Objeto nao e nulo");
	}

	@Test
	public void testSave() throws Exception {
		Hero h = createHero();
		when(heroService.saveHero(h)).thenReturn(h);
		assertNotNull(heroController.saveHero(h), "Objeto nao e nulo");
	}

	@Test
	public void testSaveNameExists() throws Exception {
		Hero h = createHero();
		List<Hero> listHero = new ArrayList<Hero>();
		listHero.add(h);
		String name = "Heroi 1";
		when(heroService.findByName(name)).thenReturn(listHero);
		assertNotNull(heroController.saveHero(h), "Objeto nao e nulo");
	}

	@Test
	public void testEditException() throws Exception {
		Hero h = createHero();
		Optional<Hero> optHero = Optional.of(h);
		when(heroService.findById(h.getId())).thenReturn(optHero);
		when(heroService.editHero(h)).thenThrow(NullPointerException.class);
		assertNotNull(heroController.editHero(h), "Objeto nao e nulo");
	}

	@Test
	public void testEdit() throws Exception {
		Hero h2 = createHero();
		Optional<Hero> optHero2 = Optional.of(h2);
		when(heroService.findById(h2.getId())).thenReturn(optHero2);
		when(heroService.editHero(h2)).thenReturn(h2);
		assertNotNull(heroController.editHero(h2), "Objeto nao e nulo");
	}

	@Test
	public void testEditNotFound() throws Exception {
		Hero h3 = createHero();
		when(heroService.findById(h3.getId())).thenReturn(Optional.empty());
		assertNotNull(heroController.editHero(h3), "Objeto nao e nulo");
	}

	@Test
	public void testDeleteExcpetion() throws Exception {
		Hero h = createHero();
		when(heroService.deleteHero(h)).thenThrow(NullPointerException.class);
		assertNotNull(heroController.deleteHero(h), "Objeto nao e nulo");
	}

	@Test
	public void testDelete() throws Exception {
		Hero h = createHero();
		when(heroService.deleteHero(h)).thenReturn("Deletado");
		assertNotNull(heroController.deleteHero(h), "Objeto nao e nulo");
	}

	@Test
	public void testCompareException() throws Exception {
		Hero h1 = createHero();
		Hero h2 = createHero();
		CompareHero compareHero = new CompareHero();
		compareHero.setHero1(h1.getId());
		compareHero.setHero2(h2.getId());
		when(heroService.findById(h1.getId())).thenThrow(NullPointerException.class);
		assertNotNull(heroController.compareHero(compareHero), "Objeto nao e nulo");
	}

	@Test
	public void testCompare() throws Exception {
		Hero h1 = createHero();
		Hero h2 = createHero();
		CompareHero compareHero = new CompareHero();
		compareHero.setHero1(h1.getId());
		compareHero.setHero2(h2.getId());

		Optional<Hero> optHero1 = Optional.of(h1);
		Optional<Hero> optHero2 = Optional.of(h2);
		when(heroService.findById(h1.getId())).thenReturn(optHero1);
		when(heroService.findById(h2.getId())).thenReturn(optHero2);
		when(heroService.compareHero(compareHero, optHero1.get(), optHero2.get())).thenReturn(compareHero);
		assertNotNull(heroController.compareHero(compareHero), "Objeto nao e nulo");
	}

	@Test
	public void testCompareHero1NotExists() throws Exception {
		Hero h1 = createHero();
		Hero h2 = createHero();
		CompareHero compareHero = new CompareHero();
		compareHero.setHero1(h1.getId());
		compareHero.setHero2(h2.getId());

		Optional<Hero> optHero1 = Optional.empty();
		when(heroService.findById(h1.getId())).thenReturn(optHero1);
		assertNotNull(heroController.compareHero(compareHero), "Objeto nao e nulo");
	}

	@Test
	public void testCompareHero2NotExists() throws Exception {
		Hero h1 = createHero();
		Hero h2 = createHero();
		CompareHero compareHero = new CompareHero();
		compareHero.setHero1(h1.getId());
		compareHero.setHero2(h2.getId());

		Optional<Hero> optHero1 = Optional.of(h2);
		Optional<Hero> optHero2 = Optional.empty();
		when(heroService.findById(h1.getId())).thenReturn(optHero1);
		when(heroService.findById(h2.getId())).thenReturn(optHero2);
		assertNotNull(heroController.compareHero(compareHero), "Objeto nao e nulo");
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
