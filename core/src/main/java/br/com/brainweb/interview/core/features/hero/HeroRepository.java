package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;

import java.util.Optional;

public interface HeroRepository {

    Optional<Hero> findById(String id);

    void create(Hero hero);

    void update(Hero hero);

}
