package br.com.brainweb.interview.core.features.hero;


import br.com.brainweb.interview.core.exceptions.DuplicatedHeroNameException;
import br.com.brainweb.interview.core.exceptions.HeroNotFoundException;
import br.com.brainweb.interview.core.features.hero.dto.CompareHeroResponse;
import br.com.brainweb.interview.core.features.powerstats.PowerStatsService;
import br.com.brainweb.interview.core.utils.Constants;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.enums.RaceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HeroServiceTest {

    private final static String id = "8a3b7e85-5bf2-42c9-9642-3b9d5eb350da";

    @InjectMocks
    HeroService heroService;

    @Mock
    HeroRepository heroRepository;

    @Mock
    PowerStatsService powerStatsService;



    @Test
    void shouldCreateValidHero() {
        when(heroRepository.findByName(anyString())).thenReturn(Arrays.asList());
        when(heroRepository.save(any())).thenReturn(Utils.getValidHero());

        Hero hero = Utils.getValidHero();
        hero.setId(null);

        heroService.createHero(hero);

        verify(heroRepository, times(1)).findByName(anyString());
        verify(heroRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenDuplicatedHeroName() {
        when(heroRepository.findByName(anyString())).thenReturn(Arrays.asList(Utils.getValidHero()));

        Hero hero = Utils.getValidHero();
        hero.setId(null);

        RuntimeException runtimeException = assertThrows(DuplicatedHeroNameException.class, () -> heroService.createHero(hero));

        Assertions.assertEquals(Constants.DUPLICATED_HERO_NAME_MESSAGE, runtimeException.getMessage());
        verify(heroRepository, only()).findByName(anyString());
        verify(heroRepository, never()).save(any());
    }

    @Test
    void shouldFindHeroById() {
        Optional<Hero> heroOpt = Optional.of((Hero) Utils.getValidHero());
        when(heroRepository.findById(any())).thenReturn(heroOpt);

        Hero hero = heroService.findById(id);

        verify(heroRepository, times(1)).findById(any());
        assertNotNull(hero);
    }

    @Test
    void shouldThrowExceptionWhenNotFoundHeroById() {
        when(heroRepository.findById(any())).thenReturn(Optional.empty());

        RuntimeException runtimeException = assertThrows(HeroNotFoundException.class, () -> heroService.findById(id));

        Assertions.assertEquals(Constants.HERO_NOT_FOUND_MESSAGE, runtimeException.getMessage());
        verify(heroRepository, times(1)).findById(any());
    }

    @Test
    void shouldUpdateHero() {
        Hero heroToBeUpdated = Utils.getValidHero();
        Hero newHeroMock = Utils.getValidHero();
        newHeroMock.setId(heroToBeUpdated.getId());
        newHeroMock.setName("Teste nome editado");


        when(heroRepository.findById(any())).thenReturn(Optional.of((Hero) heroToBeUpdated));
        when(heroRepository.save(any())).thenReturn(newHeroMock);
        when(powerStatsService.updatePowerAttributes(any(), any())).thenReturn(heroToBeUpdated.getPowerStats());

        Hero heroResponse = heroService.update(heroToBeUpdated.getId().toString(), Hero.builder().name("Teste nome editado").build());

        // assert if the name has been updated
        assertEquals("Teste nome editado", heroResponse.getName());

        // assert if the rest of attributes has not been changed
        assertEquals(heroToBeUpdated.getRace(), heroResponse.getRace());
        assertEquals(heroToBeUpdated.getId().toString(), heroResponse.getId().toString());

        verify(heroRepository, times(1)).findById(any());
        verify(heroRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenNotFoundOnUpdateHero() {
        when(heroRepository.findById(any())).thenReturn(Optional.empty());

        RuntimeException runtimeException = assertThrows(HeroNotFoundException.class,
                () -> heroService.update(Utils.getValidHero().getId().toString(), Hero.builder().name("Teste2").build()));

        Assertions.assertEquals(Constants.HERO_NOT_FOUND_MESSAGE, runtimeException.getMessage());
        verify(heroRepository, times(1)).findById(any());
        verify(heroRepository, never()).save(any());
    }

    @Test
    void shouldDeleteHero() {
        Optional<Hero> heroOpt = Optional.of((Hero) Utils.getValidHero());
        when(heroRepository.findById(any())).thenReturn(heroOpt);
        doNothing().when(heroRepository).delete(any());

        heroService.delete(Utils.getValidHero().getId().toString());

        verify(heroRepository, times(1)).findById(any());
        verify(heroRepository, times(1)).delete(any());
    }

    @Test
    void shouldThrowExceptionWhenNotFoundOnDeleteHero() {
        when(heroRepository.findById(any())).thenReturn(Optional.empty());

        RuntimeException runtimeException = assertThrows(HeroNotFoundException.class,
                () -> heroService.delete(Utils.getValidHero().getId().toString()));

        Assertions.assertEquals(Constants.HERO_NOT_FOUND_MESSAGE, runtimeException.getMessage());
        verify(heroRepository, times(1)).findById(any());
        verify(heroRepository, never()).delete(any());
    }

    @Test
    void shouldCompareTwoHeroes() {
        Hero hero1 = Utils.getValidHero();
        PowerStats power = Utils.generateRandomPower();
        Hero hero2 = new Hero(UUID.fromString(id), "teste-hero-2", RaceType.ALIEN, true,
                Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), power);

        when(heroRepository.findById(hero1.getId())).thenReturn(Optional.of((Hero) hero1));
        when(heroRepository.findById(hero2.getId())).thenReturn(Optional.of((Hero) hero2));
        when(powerStatsService.compare(any(), any())).thenReturn(power);

        CompareHeroResponse compareResult = heroService.compare(hero1.getId().toString(), hero2.getId().toString());

        verify(heroRepository, times(2)).findById(any());
        assertEquals(power.getStrength(), compareResult.getStrength());
        assertEquals(power.getAgility(), compareResult.getAgility());
        assertEquals(power.getDexterity(), compareResult.getDexterity());
        assertEquals(power.getIntelligence(), compareResult.getIntelligence());
    }

    @Test
    void shouldThrowExceptionWhenNotFoundHeroOnCompare() {
        when(heroRepository.findById(any())).thenReturn(Optional.empty());

        RuntimeException runtimeException = assertThrows(HeroNotFoundException.class,
                () -> heroService.compare(Utils.getValidHero().getId().toString(), Utils.getValidHero().getId().toString()));

        Assertions.assertEquals(Constants.HERO_NOT_FOUND_MESSAGE, runtimeException.getMessage());
        verify(heroRepository, times(1)).findById(any());
        verify(powerStatsService, never()).compare(any(), any());
    }
}
