package br.com.brainweb.interview.core.features.hero;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brainweb.interview.model.Hero;

public interface HeroRepository extends JpaRepository<Hero, UUID>{

	Hero findByName(String name);
	
}
