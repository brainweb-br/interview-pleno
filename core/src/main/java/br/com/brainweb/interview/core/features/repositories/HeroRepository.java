package br.com.brainweb.interview.core.features.repositories;

import br.com.brainweb.interview.model.entities.Hero;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HeroRepository extends PagingAndSortingRepository<Hero, UUID> {

    Optional<List<Hero>> findByName(String name);

}
