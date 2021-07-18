package br.com.brainweb.interview.core.features.hero.adapter;

import br.com.brainweb.interview.core.features.hero.HeroRepository;
import br.com.brainweb.interview.core.features.hero.HeroService;
import br.com.brainweb.interview.core.features.hero.exception.HeroNotFoundException;
import br.com.brainweb.interview.model.Hero;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class HeroServiceImpl implements HeroService {

    private final HeroRepository heroRepository;

    @Override
    public Hero findById(UUID id) {
        return heroRepository.findById(id)
                .orElseThrow(HeroNotFoundException::new);
    }

    @Override
    public List<Hero> search(String name) {
        var heroes = heroRepository.search(name);
        if (heroes.isEmpty()) {
            throw new HeroNotFoundException();
        }
        return heroes;
    }

    @Override
    public String create(Hero hero) {
        heroRepository.create(hero);
        return hero.getIdString();
    }

    @Override
    public void update(UUID id,
                       Hero hero) {
        heroRepository.findById(id)
                .map(savedHero -> {
                    savedHero.update(hero);
                    heroRepository.update(savedHero);
                    return savedHero;
                }).orElseThrow(HeroNotFoundException::new);
    }
}
