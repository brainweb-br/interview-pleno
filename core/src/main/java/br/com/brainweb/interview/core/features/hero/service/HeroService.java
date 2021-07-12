package br.com.brainweb.interview.core.features.hero.service;

import br.com.brainweb.interview.core.features.hero.dto.HerosDiffDTO;
import br.com.brainweb.interview.core.features.hero.exception.HeroNotFoundException;
import br.com.brainweb.interview.core.features.hero.repository.HeroRepository;
import br.com.brainweb.interview.model.Hero;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Service
public class HeroService {

    private final HeroRepository heroRepository;

    public Hero saveHero(Hero hero) {
        return heroRepository.save(hero);
    }

    @Cacheable(cacheNames = "Hero", key="#heroId")
    public Hero findById(UUID heroId) {
        return heroRepository.findById(heroId)
                .orElseThrow(() -> new HeroNotFoundException("Hero Not Found"));
    }

    public Optional<Hero> findHeroByName(String heroName) {
        return heroRepository.findOneByNameIgnoreCase(heroName);
    }

    @CachePut(cacheNames = "Hero", key="#hero.id")
    public Hero updateHero(Hero hero) {
        this.findById(hero.getId());
        return heroRepository.save(hero);
    }

    @CacheEvict(cacheNames = "Hero", key="#heroId")
    public void deleteHero(UUID heroId) {
        heroRepository.delete(this.findById(heroId));
    }

    public HerosDiffDTO compareHeros(String heroName1, String heroName2) {
        Hero hero1 = this.findHeroByName(heroName1)
                .orElseThrow(() -> new HeroNotFoundException(String.format("Hero '%s' Not Found.", heroName1)));
        Hero hero2 = this.findHeroByName(heroName2)
                .orElseThrow(() -> new HeroNotFoundException(String.format("Hero '%s' Not Found.", heroName2)));

        return HerosDiffDTO.builder()
                .heroId1(hero1.getId())
                .heroId2(hero2.getId())
                .strengthDiff(hero1.getPowerStats().getStrength() - hero2.getPowerStats().getStrength())
                .agilityDiff(hero1.getPowerStats().getAgility() - hero2.getPowerStats().getAgility())
                .intelligenceDiff(hero1.getPowerStats().getIntelligence() - hero2.getPowerStats().getIntelligence())
                .dexterityDiff(hero1.getPowerStats().getDexterity() - hero2.getPowerStats().getDexterity())
                .build();

    }
}