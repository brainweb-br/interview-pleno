package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HeroRepository {

    Optional<Hero> findById(UUID id);

    List<Hero> search(String name);

    void create(Hero hero);

    void update(Hero hero);

}
