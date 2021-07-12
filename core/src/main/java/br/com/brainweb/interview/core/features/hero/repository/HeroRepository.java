package br.com.brainweb.interview.core.features.hero.repository;

import br.com.brainweb.interview.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HeroRepository extends JpaRepository<Hero, UUID> {

    Optional<Hero> findOneByNameIgnoreCase(String name);

}
