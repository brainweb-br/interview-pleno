package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;

import java.util.List;
import java.util.UUID;

public interface HeroService {

    Hero findById(UUID id);

    List<Hero> search(String name);

    String create(Hero hero);

    void update(UUID id,
                Hero hero);

}
