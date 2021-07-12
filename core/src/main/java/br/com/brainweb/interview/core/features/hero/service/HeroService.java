package br.com.brainweb.interview.core.features.hero.service;

import br.com.brainweb.interview.core.features.hero.repository.HeroRepository;
import br.com.brainweb.interview.model.Hero;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HeroService {

    private final HeroRepository heroRepository;

    public Hero saveHero(Hero hero) {
        return heroRepository.save(hero);
    }
}
