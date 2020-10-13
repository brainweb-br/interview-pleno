package br.com.brainweb.interview.core.features.hero;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HeroRepository extends CrudRepository<HeroModel, UUID>{

    Iterable<HeroModel> findByName(String s);
}
