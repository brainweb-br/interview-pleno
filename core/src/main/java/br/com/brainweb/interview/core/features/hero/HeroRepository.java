package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface HeroRepository extends CrudRepository<Hero, UUID> {

}