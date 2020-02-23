package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface HeroRepository extends CrudRepository<Hero, UUID> {
    /**
     * Consulta heróis por nome
     * @param name
     * @return Optional<Hero>
     */
    public Optional<Hero> findByName(String name);
}