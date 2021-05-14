package br.com.brainweb.interview.core.features.hero;


import br.com.brainweb.interview.core.exceptions.DuplicatedHeroNameException;
import br.com.brainweb.interview.core.exceptions.HeroNotFoundException;
import br.com.brainweb.interview.core.utils.Constants;
import br.com.brainweb.interview.model.Hero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class HeroServiceTest {

    private final static String id = "8a3b7e85-5bf2-42c9-9642-3b9d5eb350da";

    @InjectMocks
    HeroService heroService;

    @Mock
    HeroRepository heroRepository;

    @Test
    void shouldCreateValidHero() {
        when(heroRepository.findByName(anyString())).thenReturn(null);
        when(heroRepository.save(any())).thenReturn(Utils.getValidHero());

        Hero hero = Utils.getValidHero();
        hero.setId(null);

        heroService.createHero(hero);

        verify(heroRepository, times(1)).findByName(anyString());
        verify(heroRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenDuplicatedHeroName() {
        when(heroRepository.findByName(anyString())).thenReturn(Utils.getValidHero());

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

        Hero hero = heroService.getHeroById(id);

        verify(heroRepository, times(1)).findById(any());
        assertNotNull(hero);
    }

    @Test
    void shouldThrowExceptionWhenNotFoundHeroById() {
        when(heroRepository.findById(any())).thenReturn(Optional.empty());

        RuntimeException runtimeException = assertThrows(HeroNotFoundException.class, () -> heroService.getHeroById(id));

        Assertions.assertEquals(Constants.HERO_NOT_FOUND_MESSAGE, runtimeException.getMessage());
        verify(heroRepository, times(1)).findById(any());
    }


}
