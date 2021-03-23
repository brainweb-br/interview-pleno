package br.com.brainweb.interview.core.features.hero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;


import br.com.brainweb.interview.core.features.hero.exception.BusinessException;
import br.com.brainweb.interview.core.features.hero.repository.HeroRepository;
import br.com.brainweb.interview.core.features.hero.service.HeroService;
import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@ActiveProfiles("it")
public class HeroServiceTest {
	
	@Mock
	private PowerStatsRepository powerStatsRepository;
	
	@Mock
	private HeroRepository heroRepository;
	
	@InjectMocks
	private HeroService heroService;
	
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        FixtureFactoryLoader.loadTemplates("br.com.brainweb.interview.core.features.hero");
	}
	
	@Test
	public void deveSalvarHero() {
		Hero hero = Fixture.from(Hero.class).gimme("valid-hero");
		
		Hero createdHero = heroService.save(hero);
		
		assertNotNull(createdHero);
		Mockito.verify(heroRepository, times(1)).save(Mockito.any(Hero.class));
		Mockito.verify(powerStatsRepository, times(1)).save(Mockito.any(PowerStats.class));
	}
	
	@Test
	public void deveAtualizarHero() {
		UUID id = UUID.fromString("aa16b7f1-3ea5-4776-ac4a-5c054d148dd3");
		Hero hero = Fixture.from(Hero.class).gimme("valid-hero");
		
		Mockito.when(heroRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(hero));
		Optional<Hero> optionalHero = heroService.update(hero, id.toString());
		
		assertTrue(optionalHero.isPresent());
		Mockito.verify(heroRepository, times(1)).update(Mockito.any(Hero.class));
		Mockito.verify(powerStatsRepository, times(1)).update(Mockito.any(PowerStats.class));
	}
	
	@Test
	public void deveValidarQuandoIdentificadoresSaoDiferentes() {
		UUID id = UUID.randomUUID();
		Hero hero = Fixture.from(Hero.class).gimme("valid-hero");
		
		BusinessException businessException = assertThrows(BusinessException.class, () -> heroService.update(hero, id.toString()));
		
		assertEquals("Os identicadores do recurso sao diferentes.", businessException.getMessage());
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), businessException.getHttpStatus().value());
	}
	
	@Test
	public void deveRecuperarHero() {
		String id = "5a70ed27-b672-410f-8b5b-65c4701dfe5e";
		Hero hero = Fixture.from(Hero.class).gimme("valid-hero");
		
		Mockito.when(heroRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(hero));
		
		Optional<Hero> optionalHero = heroService.findById(id);
		
		assertTrue(optionalHero.isPresent());
	}
	
	@Test
	public void deveValidarQuandoHeroNaoEncontrado() {
		String id = "5a70ed27-b672-410f-8b5b-65c4701dfe5e";
		
		Mockito.when(heroRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.empty());
		
		Optional<Hero> optionalHero = heroService.findById(id);
		
		assertFalse(optionalHero.isPresent());
	}
	
	@Test
	public void deveRecuperarHeroPeloNome() {
		String name = "aranha";
		Hero hero = Fixture.from(Hero.class).gimme("valid-hero");
		
		Mockito.when(heroRepository.findByName(Mockito.any(String.class))).thenReturn(Optional.of(hero));
		
		Optional<Hero> optionalHero = heroService.findByName(name);
		
		assertTrue(optionalHero.isPresent());
	}
	
	@Test
	public void deveValidarQuandoNaoEncontrarHeroPeloNome() {
		String name = "aranha";
		
		Mockito.when(heroRepository.findByName(Mockito.any(String.class))).thenReturn(Optional.empty());
		
		Optional<Hero> optionalHero = heroService.findByName(name);
		
		assertFalse(optionalHero.isPresent());
	}
	
	@Test
	public void deveExcluirHero() {
		String id = "aa16b7f1-3ea5-4776-ac4a-5c054d148dd3";
		Hero hero = Fixture.from(Hero.class).gimme("valid-hero");
		
		Mockito.when(heroRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(hero));
		
		heroService.delete(id);
		
		Mockito.verify(heroRepository, times(1)).delete(Mockito.any(UUID.class));
		Mockito.verify(powerStatsRepository, times(1)).delete(Mockito.any(UUID.class));
	}
	
	@Test
	public void deveValidarQuandoHeroNaoEncontradoAoExcluir() {
		String id = "aa16b7f1-3ea5-4776-ac4a-5c054d148dd3";
		Mockito.when(heroRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.empty());
		
		BusinessException businessException = assertThrows(BusinessException.class, () -> heroService.delete(id));

		
		assertEquals("Hero nao encontrado", businessException.getMessage());
		assertEquals(HttpStatus.NOT_FOUND.value(), businessException.getHttpStatus().value());
	}
}
