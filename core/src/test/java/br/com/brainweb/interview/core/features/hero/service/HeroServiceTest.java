package br.com.brainweb.interview.core.features.hero.service;

import br.com.brainweb.interview.core.features.hero.dto.HerosDiffDTO;
import br.com.brainweb.interview.core.features.hero.exception.HeroNotFoundException;
import br.com.brainweb.interview.core.features.hero.repository.HeroRepository;
import br.com.brainweb.interview.core.features.hero.utils.Utils;
import br.com.brainweb.interview.model.Hero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HeroServiceTest {

    private final static UUID id = UUID.randomUUID();

    @InjectMocks
    HeroService heroService;

    @Mock
    HeroRepository heroRepository;

    @Test
    void test_should_save_hero() {
        when(heroRepository.save(any())).thenReturn(Utils.getHeroWithoutId());

        Hero hero = Utils.getHeroWithoutId();

        heroService.saveHero(hero);
    }

    @Test
    void test_should_find_hero_by_id() {
        Optional<Hero> heroOpt = Optional.of(Utils.getHeroWithId());
        when(heroRepository.findById(any())).thenReturn(heroOpt);

        Hero hero = heroService.findById(id);

        assertNotNull(hero);
    }

    @Test
    void test_should_throw_exception_when_not_found_hero_by_id() {
        when(heroRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(HeroNotFoundException.class, () -> heroService.findById(id));
    }

    @Test
    void test_should_update_hero() {
        Hero heroToBeUpdated = Utils.getHeroWithId();

        when(heroRepository.findById(any())).thenReturn(Optional.of(heroToBeUpdated));
        when(heroRepository.save(any())).thenReturn(heroToBeUpdated);

        heroToBeUpdated.setName("editado");
        Hero heroResponse = heroService.updateHero(heroToBeUpdated);

        assertEquals("editado", heroResponse.getName());

        assertEquals(heroToBeUpdated.getRace(), heroResponse.getRace());
        assertEquals(heroToBeUpdated.getId().toString(), heroResponse.getId().toString());
    }

    @Test
    void test_should_throw_exception_when_not_found_on_updateHero() {
        when(heroRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(HeroNotFoundException.class,
                () -> heroService.updateHero(Utils.getHeroWithId()));
    }

    @Test
    void test_should_delete_hero() {
        Optional<Hero> heroOpt = Optional.of(Utils.getHeroWithId());
        when(heroRepository.findById(any())).thenReturn(heroOpt);
        doNothing().when(heroRepository).delete(any());

        heroService.deleteHero(Utils.getHeroWithId().getId());
    }

    @Test
    void test_should_throw_exception_when_not_found_on_deleteHero() {
        when(heroRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(HeroNotFoundException.class,
                () -> heroService.deleteHero(Utils.getHeroWithId().getId()));
    }

    @Test
    void test_should_compare_two_heroes() {
        Hero hero1 = Utils.getHeroWithId();

        when(heroRepository.findOneByNameIgnoreCase(hero1.getName())).thenReturn(Optional.of(hero1));
        when(heroRepository.findOneByNameIgnoreCase(hero1.getName())).thenReturn(Optional.of(hero1));

        HerosDiffDTO herosDiffDTO = heroService.compareHeros(hero1.getName(), hero1.getName());

        assertEquals(0, herosDiffDTO.getStrengthDiff());
        assertEquals(0, herosDiffDTO.getDexterityDiff());
        assertEquals(0, herosDiffDTO.getIntelligenceDiff());
        assertEquals(0, herosDiffDTO.getAgilityDiff());

    }

    @Test
    void test_should_throw_exception_when_not_found_hero_on_compare() {
        when(heroRepository.findOneByNameIgnoreCase(any())).thenReturn(Optional.empty());

        assertThrows(HeroNotFoundException.class,
                () -> heroService.compareHeros(Utils.getHeroWithId().getName(), "Nothing"));
    }
}
