package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    public List<Hero> findAllHeroes() {
        List<Hero> heroes = heroRepository.findAll();
        return heroes;
    }

    public Optional<Hero> findHeroById(UUID idHero) {
        return heroRepository.findById(idHero);
    }

    public Optional<Hero> findHeroByName(String name) {
        return heroRepository.findOne(Specification.where(HeroSpecification.nameEq(name)));
    }

    public List<Hero> findHeroFilterByName(String name) {
        return heroRepository.findAll(Specification.where(HeroSpecification.nameFilter(name)));

    }

    public Optional<Hero> findHeroByNameAndId(Hero hero) {
        return heroRepository
                .findOne(Specification.where(HeroSpecification.nameEq(hero.getName()))
                        .and((Specification.not(HeroSpecification.idEq(hero.getId())))));

    }

    @Transactional
    public Hero saveHero(Hero hero) {
        return heroRepository.save(hero);
    }

    @Transactional
    public void removeHero(Hero hero) {
        heroRepository.delete(hero);
    }

    @Transactional
    public void active(Hero hero) {
        hero.activate();
        heroRepository.save(hero);
    }

    @Transactional
    public void deactivate(Hero hero) {
        hero.deactivate();
        heroRepository.save(hero);
    }
}

