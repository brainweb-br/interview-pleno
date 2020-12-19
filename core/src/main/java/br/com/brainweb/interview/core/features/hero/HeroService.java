package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public List<Hero> findAllHeroes() {
        return heroRepository.findAll();
    }

    public Optional<Hero> findHeroById(UUID idHero) {
    return null;
    }

    public Optional<Hero> findHeroByName(String name) {
        return heroRepository.findOne(Specification.where(HeroSpecification.nameEq(name)));
    }

    public List<Hero> findHeroFilterByName(String name) {
        return heroRepository.findAll(Specification.where(HeroSpecification.nameFilter(name)));
    }

    public Optional<Hero> findHeroByNameAndId(UUID id, String name) {
        return heroRepository.findOne(Specification.where(HeroSpecification.nameEq(name)).and(HeroSpecification.idEq(id.toString())));
    }

    public Hero saveHero(Hero hero) {
        return heroRepository.save(hero);
    }


    public void removeHero(Hero hero) {
        heroRepository.delete(hero);
    }

    public void active(Hero hero) {
        hero.activate();
        heroRepository.save(hero);
    }

    public void deactivate(Hero hero) {
    hero.deactivate();
    heroRepository.save(hero);
    }

}
