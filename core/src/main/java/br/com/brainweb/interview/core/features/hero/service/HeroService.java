package br.com.brainweb.interview.core.features.hero.service;

import br.com.brainweb.interview.core.features.hero.exception.HeroNotFoundException;
import br.com.brainweb.interview.core.features.hero.repository.HeroRepository;
import br.com.brainweb.interview.model.Hero;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class HeroService {

    private final HeroRepository heroRepository;

    public Hero saveHero(Hero hero) {
        return heroRepository.save(hero);
    }

    public Hero findById(UUID heroId) {
        return heroRepository.findById(heroId)
                .orElseThrow(() -> new HeroNotFoundException("Hero Not Found"));
    }

    public Optional<Hero> findHeroByName(String heroName) {
        return heroRepository.findOneByNameIgnoreCase(heroName);
    }

    public Hero updateHero(Hero hero) {
        this.findById(hero.getId());
        return heroRepository.save(hero);
    }
}
