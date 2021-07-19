package br.com.brainweb.interview.core.features.hero.adapter;

import br.com.brainweb.interview.core.features.hero.exception.InvalidHeroException;
import br.com.brainweb.interview.model.CompareHero;
import br.com.brainweb.interview.core.features.hero.HeroRepository;
import br.com.brainweb.interview.core.features.hero.HeroService;
import br.com.brainweb.interview.core.features.hero.exception.HeroNotFoundException;
import br.com.brainweb.interview.model.Hero;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

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
        validName(hero.getName(), hero.getId());
        heroRepository.create(hero);
        return hero.getIdString();
    }

    @Override
    public Hero update(UUID id,
                       Hero hero) {
        validName(hero.getName(), hero.getId());
        return heroRepository.findById(id)
                .map(savedHero -> {
                    savedHero.update(hero);
                    heroRepository.update(savedHero);
                    return savedHero;
                }).orElseThrow(HeroNotFoundException::new);
    }

    private void validName(String name, UUID id) {
        heroRepository.findByName(name).ifPresent(hero -> {
            if(!hero.getId().equals(id)){
                throw new InvalidHeroException("already has a hero named " + name);
            }
        });
    }

    @Override
    public OperationResult delete(UUID id) {
        return heroRepository.findById(id)
                .map(savedHero -> {
                    heroRepository.delete(savedHero);
                    return OperationResult.SUCCESS;
                }).orElseThrow(HeroNotFoundException::new);
    }

    @Override
    public CompareHero compare(UUID heroId1, UUID heroId2) {
        var hero1 = findById(heroId1);
        var hero2 = findById(heroId2);
        return hero1.compare(hero2);
    }
}
