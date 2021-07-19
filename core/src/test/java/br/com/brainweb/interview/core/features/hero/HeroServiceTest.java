package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.hero.adapter.HeroServiceImpl;
import br.com.brainweb.interview.core.features.hero.exception.HeroNotFoundException;
import br.com.brainweb.interview.core.features.hero.exception.InvalidHeroException;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.Race;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class HeroServiceTest {


    private final HeroRepository heroRepository = new InMemoryHeroRepository();

    private final HeroService heroService = new HeroServiceImpl(heroRepository);

    @BeforeEach
    public void setup() {
        heroRepository.deleteAll();
    }

    @Test
    public void shouldCompareDiffBetweenHeroes() {
        var statsCommand1 = new PowerStats(10, 8, 9, 10);
        var statsCommand2 = new PowerStats(5, 10, 1, 5);
        var hero1 = new Hero("SuperMan", Race.DIVINE, statsCommand1, true);
        var hero2 = new Hero("Flash", Race.HUMAN, statsCommand2, true);
        heroRepository.create(hero1);
        heroRepository.create(hero2);
        var result = heroService.compare(hero1.getId(), hero2.getId());
        assertEquals(5, result.getStrengthDiff());
        assertEquals(-2, result.getAgilityDiff());
        assertEquals(8, result.getDexterityDiff());
        assertEquals(5, result.getIntelligenceDiff());
    }

    @Test
    public void shouldGetHeroById() {
        var statsCommand1 = new PowerStats(10, 8, 9, 10);
        var hero1 = new Hero("SuperMan", Race.DIVINE, statsCommand1, true);
        heroRepository.create(hero1);
        var result = heroService.findById(hero1.getId());
        assertNotNull(result);
    }

    @Test
    public void shouldThrowNotFoundWhenGetHeroById() {
        var statsCommand1 = new PowerStats(10, 8, 9, 10);
        var hero1 = new Hero("SuperMan", Race.DIVINE, statsCommand1, true);
        var hero2 = new Hero("SuperMan2", Race.DIVINE, statsCommand1, true);
        heroRepository.create(hero1);
        assertThrows(HeroNotFoundException.class, () -> {
            heroService.findById(hero2.getId());
        });
    }

    @Test
    public void shouldCreateHero() {
        var statsCommand1 = new PowerStats(10, 8, 9, 10);
        var hero1 = new Hero("SuperMan", Race.DIVINE, statsCommand1, true);
        var result = heroService.create(hero1);
        assertEquals(hero1.getId(), UUID.fromString(result));
    }

    @Test
    public void shouldThrowInvalidHeroWhenCreateHeroWithSameName() {
        var statsCommand1 = new PowerStats(10, 8, 9, 10);
        var hero1 = new Hero("SuperMan", Race.DIVINE, statsCommand1, true);
        var hero2 = new Hero("SuperMan", Race.DIVINE, statsCommand1, true);
        heroRepository.create(hero1);
        assertThrows(InvalidHeroException.class, () -> {
            heroService.create(hero2);
        });
    }

    @Test
    public void shouldUpdateHero() {
        var stats1 = new PowerStats(10, 8, 9, 10);
        var hero1 = new Hero("SuperMan", Race.DIVINE, stats1, true);
        heroRepository.create(hero1);
        hero1.setName("test");
        hero1.setRace(Race.ALIEN);
        hero1.setEnabled(false);
        var stats = new PowerStats(1, 1, 2, 10);
        hero1.setPowerStats(stats);
        var result = heroService.update(hero1.getId(), hero1);
        assertEquals("test", result.getName());
        assertEquals(Race.ALIEN, result.getRace());
        assertFalse(result.isEnabled());
        assertEquals(1, result.getPowerStats().getStrength());
        assertEquals(1, result.getPowerStats().getAgility());
        assertEquals(2, result.getPowerStats().getDexterity());
        assertEquals(10, result.getPowerStats().getIntelligence());
    }

    @Test
    public void shouldUpdateHero2() {
        var stats1 = new PowerStats(10, 8, 9, 10);
        var hero1 = new Hero("SuperMan", Race.DIVINE, stats1, true);
        heroRepository.create(hero1);
        hero1.setRace(Race.ALIEN);
        hero1.setEnabled(false);
        var stats = new PowerStats(1, 1, 2, 10);
        hero1.setPowerStats(stats);
        var result = heroService.update(hero1.getId(), hero1);
        assertEquals("SuperMan", result.getName());
        assertEquals(Race.ALIEN, result.getRace());
        assertFalse(result.isEnabled());
        assertEquals(1, result.getPowerStats().getStrength());
        assertEquals(1, result.getPowerStats().getAgility());
        assertEquals(2, result.getPowerStats().getDexterity());
        assertEquals(10, result.getPowerStats().getIntelligence());
    }

    @Test
    public void shouldThrowInvalidHeroWhenUpdateHeroWithSameName() {
        var stats1 = new PowerStats(10, 8, 9, 10);
        var hero1 = new Hero("SuperMan", Race.DIVINE, stats1, true);
        heroRepository.create(hero1);
        var hero2 = new Hero("SuperMan", Race.DIVINE, stats1, false);
        assertThrows(InvalidHeroException.class, () -> {
            heroService.update(hero2.getId(), hero2);
        });
    }

    @Test
    public void shouldDeleteAHero() {
        var stats1 = new PowerStats(10, 8, 9, 10);
        var hero1 = new Hero("SuperMan", Race.DIVINE, stats1, true);
        heroRepository.create(hero1);
        var result = heroService.delete(hero1.getId());
        assertTrue(result);
    }

    @Test
    public void shouldThrowNotFoundWhenDelete() {
        var stats1 = new PowerStats(10, 8, 9, 10);
        var hero1 = new Hero("SuperMan", Race.DIVINE, stats1, true);
        var hero2 = new Hero("SuperMan2", Race.DIVINE, stats1, true);
        heroRepository.create(hero2);
        assertThrows(HeroNotFoundException.class, () -> {
            heroService.delete(hero1.getId());
        });
    }


}
