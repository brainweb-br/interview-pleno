package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.entities.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HeroRepository extends PagingAndSortingRepository<Hero, UUID> {

    Hero findByName(String name);

}
