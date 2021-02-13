package br.com.brainweb.interview.core.features.hero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;

public class HeroControllerTest {
    
    private static final int strength = 0;
    private static final int agility = 0;
    private static final int dexterity = 0;
    private static final int intelligence = 0;

    @Test
    public void shouldNotCreateHeroBecauseHeroIsNull() {
	HeroController controller = new HeroController(null);
	
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> controller.create(null));
	assertEquals(exception.getMessage(), "Hero cannot be null.");
    }
    
    @Test
    public void shouldNotCreateHeroBecauseNameIsEmpty() {
	
	HeroController controller = new HeroController(null);
	Hero hero = new Hero("", "", null);
	
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> controller.create(hero));
	assertEquals(exception.getMessage(), "Name is required.");
    }
    
    @Test
    public void shouldNotCreateHeroBecauseHeroAlreadyExists() {
	HeroService service = Mockito.mock(HeroService.class);
	Hero heroMock = new Hero("AmazingGirl", "", null);
	Mockito.when(service.findByName("AmazingGirl")).thenReturn(heroMock );
	
	HeroController controller = new HeroController(service);
	Hero hero = new Hero("AmazingGirl", "", null);
	
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> controller.create(hero));
	assertEquals(exception.getMessage(), "A hero with name AmazingGirl already exists.");
    }
    
    @Test
    public void shouldNotCreateHeroBecauseRaceIsEmpty() {
	HeroService service = Mockito.mock(HeroService.class);
	Mockito.when(service.findByName("AmazingGirl")).thenReturn(null );
	
	HeroController controller = new HeroController(service);
	Hero hero = new Hero("AmazingGirl", "", null);
	
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> controller.create(hero));
	assertEquals(exception.getMessage(), "Race is required.");
    }
    
    @Test
    public void shouldCreateHero() {
	HeroService service = Mockito.mock(HeroService.class);
	Mockito.doNothing().when(service).create(Mockito.any());
	
	HeroController controller = new HeroController(service);
	Hero hero = new Hero("AmazingGirl", "HUMAN", new PowerStats(strength, agility, dexterity, intelligence));
	
	controller.create(hero);
    }
    
    @Test
    public void shouldReturnHeroById() {
	Hero heroMock = new Hero("AmazingGirl", "", null);
	
	HeroService service = Mockito.mock(HeroService.class);
	Mockito.when(service.findById(Mockito.any())).thenReturn(heroMock);
	
	HeroController controller = new HeroController(service);
	
	Hero hero = controller.findById(UUID.randomUUID().toString());
	assertNotNull(hero);
    }
    
    @Test
    public void shouldThrowExceptionHeroNotFound() {
	HeroService service = Mockito.mock(HeroService.class);
	Mockito.when(service.findById(Mockito.any())).thenReturn(null);
	HeroController controller = new HeroController(service);
	assertThrows(HeroNotFoundException.class, () -> controller.findById(UUID.randomUUID().toString()));
    }
    
    @Test
    public void shouldNotFindHeroByNameBecauseNameIsNull() {
	HeroController controller = new HeroController(null);
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> controller.findByName(null));
	assertEquals(exception.getMessage(), "Name is required.");
    }
    
    @Test
    public void shouldFindHeroByName() {
	Hero heroMock = new Hero("AmazingGirl", "HUMAN", new PowerStats(strength, agility, dexterity, intelligence));
	
	HeroService service = Mockito.mock(HeroService.class);
	Mockito.when(service.findByName(Mockito.anyString())).thenReturn(heroMock);
	
	HeroController controller = new HeroController(service);
	
	Hero result = controller.findByName("AmazingGirl");
	assertNotNull(result);
    }
    
    @Test
    public void shouldNotUpdateHeroBecauseHeroIsNull() {
	HeroController controller = new HeroController(null);
	
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> controller.update(null));
	assertEquals(exception.getMessage(), "Hero cannot be null.");
    }
    
    @Test
    public void shouldNotUpdateHeroBecauseIdIsNull() {
	HeroController controller = new HeroController(null);
	
	Hero hero = new Hero("AmazingGirl", "HUMAN", null);
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> controller.update(hero));
	assertEquals(exception.getMessage(), "Id cannot be null.");
    }
    
    @Test
    public void shouldNotUpdateHeroBecauseHeroHasNotFound() {
	
	HeroService service = Mockito.mock(HeroService.class);
	Mockito.when(service.findById(Mockito.any())).thenReturn(null);
	HeroController controller = new HeroController(service);
	
	Hero hero = new Hero(UUID.randomUUID(), "AmazingGirl", "HUMAN", new PowerStats(strength, agility, dexterity, intelligence));
	assertThrows(HeroNotFoundException.class, () -> controller.update(hero));
    }
    
    @Test
    public void shouldUpdateHero() {
	Hero heroMock = new Hero("AmazingGirl", "", null);
	HeroService service = Mockito.mock(HeroService.class);
	Mockito.when(service.findById(Mockito.any())).thenReturn(heroMock);
	
	HeroController controller = new HeroController(service);
	Hero hero = new Hero(UUID.randomUUID(), "AmazingGirl", "HUMAN", new PowerStats(strength, agility, dexterity, intelligence));
	
	controller.update(hero);
    }
    
    @Test
    public void shouldNotDeleteHeroBecauseIdIsNull() {
	HeroController controller = new HeroController(null);
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> controller.delete(null));
	assertEquals(exception.getMessage(), "Id cannot be null.");
    }
    
    @Test
    public void shouldNotDeleteHeroBecauseHeroHasNotFound() {
	HeroService service = Mockito.mock(HeroService.class);
	Mockito.when(service.findById(Mockito.any())).thenReturn(null);
	HeroController controller = new HeroController(service);
	assertThrows(HeroNotFoundException.class, () -> controller.delete(UUID.randomUUID().toString()));
    }
    
    @Test
    public void shouldDeleteHero() {
	Hero heroMock = new Hero("AmazingGirl", "", null);
	HeroService service = Mockito.mock(HeroService.class);
	Mockito.when(service.findById(Mockito.any())).thenReturn(heroMock);
	HeroController controller = new HeroController(service);
	controller.delete(UUID.randomUUID().toString());
    }
}
