package br.com.brainweb.interview.core.features.hero;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brainweb.interview.model.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, UUID> {
	
	public List<Hero> findByNameIgnoreCaseContaining(String namel);

}