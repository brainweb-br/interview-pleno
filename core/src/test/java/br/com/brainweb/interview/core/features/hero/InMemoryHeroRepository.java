package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.Race;

import java.util.*;

public class InMemoryHeroRepository implements HeroRepository{

    private final Set<Hero> heroes = new LinkedHashSet<>();

    @Override
    public List<Hero> search(String name) {
        return null;
    }

    @Override
    public Optional<Hero> findById(UUID id) {
        return heroes.stream().filter(hero -> hero.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Hero> findByName(String name) {
        return heroes.stream().filter(hero -> hero.getName().equals(name)).findFirst();
    }

    @Override
    public void create(Hero hero) {
        var stats = hero.getPowerStats();
        var addStats = new PowerStats(stats.getStrength(), stats.getAgility(), stats.getDexterity(), stats.getIntelligence());
        var addHero = new Hero(hero.getName(), hero.getRace(), addStats, hero.isEnabled());
        addHero.setId(hero.getId());
        this.heroes.add(addHero);
    }

    @Override
    public void deleteAll() {
        this.heroes.clear();
    }

    @Override
    public void delete(Hero hero) {

    }

    @Override
    public void update(Hero hero) {
        findById(hero.getId()).ifPresent(hero1 -> {
            this.heroes.remove(hero1);
            this.heroes.add(hero);
        });
    }
}
