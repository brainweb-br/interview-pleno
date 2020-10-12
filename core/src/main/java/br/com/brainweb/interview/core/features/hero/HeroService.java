package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroService {


    public Optional<List<Hero>> listAll() {
        return Optional.empty();
    }

    public Optional<Hero> getById(Long id) {
        return Optional.empty();
    }

    public Optional<Hero> save() {
        return Optional.empty();
    }

    public Optional<Hero> update() {
        return Optional.empty();
    }

    public Optional<Hero> remove() {
        return Optional.empty();
    }

}
