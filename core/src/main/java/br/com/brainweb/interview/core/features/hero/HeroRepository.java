package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HeroRepository extends JpaRepository<Hero, UUID> {

	@Query("SELECT h FROM Hero h WHERE lower(h.name) like lower(:name || '%')")
	public List<Hero> findAllByName(@Param("name") String name);

	@Query("SELECT true FROM Hero h WHERE h.name = :name")
	public Optional<Boolean> existsByName(@Param("name") String name);

	@Query("SELECT h FROM Hero h WHERE h.name = :name")
	public Optional<Hero> findByName(@Param("name") String name);
}
