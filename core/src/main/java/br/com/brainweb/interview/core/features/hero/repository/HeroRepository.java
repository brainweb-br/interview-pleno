package br.com.brainweb.interview.core.features.hero.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import br.com.brainweb.interview.core.features.hero.exception.GeneralFailureException;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;

@Repository
public class HeroRepository {
	
	public static final String INSERT_HERO = "insert into hero(id, name, race, power_stats_id, enabled, created_at, updated_at) values (?, ?, ?, ?, ?, ?, ?)";
	public static final String SELECT_HERO = "select * from hero where id = ?";
	public static final String SELECT_HERO2 = "select h.*, ps.agility, ps.intelligence, ps.strength, ps.dexterity, ps.created_at as power_st_created, ps.updated_at as power_st_updated from hero h, power_stats ps where h.id = ? and h.power_stats_id = ps.id";
	
	public static final String SELECT_HERO_BY_NAME = "select * from hero where name like ?";
	public static final String DELETE_HERO = "delete from hero where id = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void save(Hero hero) {
		try {
			PowerStats powerStats = hero.getPowerStats();
			
			jdbcTemplate.update(INSERT_HERO,
					hero.getId(),
					hero.getName(),
					hero.getRace(),
					powerStats.getId(),
					hero.isEnable(),
					hero.getCreated_at(),
					hero.getUpdated_at());
		} catch (Exception e) {
			throw new GeneralFailureException("Erro ao inserir hero");
		}
	}
	
	public Optional<Hero> findById(UUID id) {
		try {
			return Optional.of((Hero) jdbcTemplate.queryForObject(SELECT_HERO2,
					new Object[]{id},
					new HeroRowMapper()));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		} catch (Exception e) {
			throw new GeneralFailureException("Erro ao consultar hero.");
		}
	}
	
	public List<Hero> findByName(String name) {
		try {
			return jdbcTemplate.query(SELECT_HERO_BY_NAME,
					new Object[]{name},
					new HeroRowMapper());
		} catch (Exception e) {
			throw new GeneralFailureException("Erro ao consultar hero pelo nome.");
		}
	}
	
	public int delete(String id) {
		UUID uuid = UUID.fromString(id);
		
		try {
			return jdbcTemplate.update(DELETE_HERO,
					new Object[]{uuid});
		} catch (Exception e) {
			throw new GeneralFailureException("Erro ao consultar hero pelo nome.");
		}
	}
}
