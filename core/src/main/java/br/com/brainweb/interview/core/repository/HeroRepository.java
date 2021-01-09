package br.com.brainweb.interview.core.repository;

import br.com.brainweb.interview.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HeroRepository extends JpaRepository<Hero, UUID> {

	List<Hero> findByNameIgnoreCase(String name);
	List<Hero> findByIds(List<UUID> ids);
	
	
}
