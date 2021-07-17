package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;

public interface HeroService {

    Hero findById(String id);

    String create(Hero hero);

    void update(Hero hero);

}
