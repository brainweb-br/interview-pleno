package br.com.brainweb.interview.core.features.hero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brainweb.interview.model.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {

	Hero findById(int id);

	Hero findByName(String name);
	

}
