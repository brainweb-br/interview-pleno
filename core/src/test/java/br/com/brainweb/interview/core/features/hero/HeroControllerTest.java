package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.hero.controller.HeroController;
import br.com.brainweb.interview.core.features.hero.service.HeroService;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.CompareHero;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.http.HttpStatus;

import java.util.*;

import static br.com.brainweb.interview.core.constants.ApiConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
public class HeroControllerTest {

	@Mock
	private HeroService heroService;

	@InjectMocks
	private HeroController heroController;


	@Test
	public void testFindById() {
		Hero hero = createHero();
		UUID id2 = UUID.fromString("85ef9d3f-7be2-4499-ad15-04a996f6554d");
		when(heroService.findById(id2)).thenReturn(Optional.of(hero));
		assertEquals(heroController.findById(id2).getStatusCode().value(),HttpStatus.OK.value());
		assertEquals(heroController.findById(id2).getBody(),Optional.of(hero));
	}

	@Test
	public void testDelete(){
		Hero hero = createHero();
		when(heroService.findById(hero.getId())).thenReturn(Optional.of(hero));
		assertEquals(heroController.deleteHero(hero.getId()).getStatusCode().value(),HttpStatus.OK.value());
		assertEquals(heroController.deleteHero(hero.getId()).getBody(),HERO_DELETE);
	}


	@Test
	public void testDeleteNotFoundHero(){
		Hero hero = createHero();
		when(heroService.findById(hero.getId())).thenReturn(Optional.empty());
		assertEquals(heroController.deleteHero(hero.getId()).getStatusCode().value(),HttpStatus.NOT_FOUND.value());
		assertEquals(heroController.deleteHero(hero.getId()).getBody(), HERO_NOT_FOUND);
	}

	@Test
	public void testFindByIdNotFound() {
		UUID id2 = UUID.fromString("85ef9d3f-7be2-4499-ad15-04a996f6554d");
		when(heroService.findById(id2)).thenReturn(Optional.empty());
		assertEquals(heroController.findById(id2).getStatusCode().value(),HttpStatus.NOT_FOUND.value());
		assertEquals(heroController.findById(id2).getBody(), HERO_NOT_FOUND);
	}


	@Test
	public void testFindByName() {
		List<Hero> listHero = new ArrayList<Hero>();
		String name = "Heroi 1";
		listHero.add(createHero());
		when(heroService.findByName(name)).thenReturn(listHero);
		assertEquals(heroController.findByName(name).getStatusCode().value(),HttpStatus.OK.value());
		assertEquals(heroController.findByName(name).getBody(),listHero);
	}


	@Test
	public void testNotFindByName() {

		when(heroService.findByName(any())).thenReturn(Arrays.asList());
		assertEquals(heroController.findByName(any()).getStatusCode().value(),HttpStatus.OK.value());
		assertEquals(heroController.findByName(any()).getBody(),Arrays.asList());
	}

	@Test
	public void testSave() {
		List<Hero> listHero = new ArrayList<Hero>();
		Hero hero = createHero();
		when(heroService.findByName(hero.getName())).thenReturn(listHero);
		assertEquals(heroController.createHero(createHero()).getStatusCodeValue(),HttpStatus.OK.value());
	}

	@Test
	public void testSaveNameExists() {
		List<Hero> listHero = new ArrayList<Hero>();
		listHero.add(createHero());
		String name = "Heroi 1";
		when(heroService.findByName(name)).thenReturn(listHero);
		assertEquals(heroController.createHero(createHero()).getStatusCodeValue(),HttpStatus.BAD_REQUEST.value());
		assertEquals(heroController.createHero(createHero()).getBody(), HERO_NAME_EXISTS);
	}



	@Test
	public void testEdit() {
		Hero h2 = createHero();
		Optional<Hero> optHero2 = Optional.of(h2);
		when(heroService.findById(h2.getId())).thenReturn(optHero2);
		when(heroService.editHero(h2)).thenReturn(h2);
		assertEquals(heroController.editHero(h2).getStatusCodeValue(), HttpStatus.OK.value());
	}

	@Test
	public void testEditNotFound() {
		Hero h2 = createHero();
		h2.setId(null);
		assertEquals(heroController.editHero(h2).getStatusCodeValue(), HttpStatus.NOT_FOUND.value());
		assertEquals(heroController.editHero(h2).getBody(), HERO_NOT_FOUND);
	}


	@Test
	public void testCompareSucess()
	{
		Hero h1 = createHero();
		Hero h2 = createHero();
		CompareHero compareHero = new CompareHero();
		when(heroService.compareHeroes(h1.getId(),h2.getId())).thenReturn(compareHero);
		assertEquals(heroController.compareHeroes(h1.getId(),h2.getId()).getStatusCode(),HttpStatus.OK);
	}

	@Test
	public void testCompareIfNotExistsHero2()
	{
		Hero h1 = createHero();
		Hero h2 = createHero();
		when(heroService.compareHeroes(h1.getId(),h2.getId())).thenReturn(null);
		assertEquals(heroController.compareHeroes(h1.getId(),h2.getId()).getStatusCode(),HttpStatus.NOT_FOUND);
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
