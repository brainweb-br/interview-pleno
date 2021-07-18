package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;

import java.util.Optional;
import java.util.UUID;

public interface HeroRepository {

    Optional<Hero> findById(UUID id);

    void create(Hero hero);

    void update(Hero hero);

}
