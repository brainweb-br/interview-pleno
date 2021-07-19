package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.CompareHero;
import br.com.brainweb.interview.model.Hero;

import java.util.List;
import java.util.UUID;

public interface HeroService {

    Hero findById(UUID id);

    List<Hero> search(String name);

    String create(Hero hero);

    Hero update(UUID id,
                Hero hero);

    boolean delete(UUID id);

    CompareHero compare(UUID heroId1,
                        UUID heroId2);


}
