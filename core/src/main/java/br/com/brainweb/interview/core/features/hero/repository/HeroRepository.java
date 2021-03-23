package br.com.brainweb.interview.core.features.hero.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.brainweb.interview.core.features.hero.exception.GeneralFailureException;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class HeroRepository {
	
	public static final String INSERT_HERO = "insert into hero(id, name, race, power_stats_id, enabled, created_at, updated_at) values (?, ?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_HERO = "update hero set name = ?, race = ?, enabled = ?, updated_at = ? where id = ?";
	public static final String SELECT_HERO = "select h.*, ps.agility, ps.intelligence, ps.strength, ps.dexterity, ps.created_at as power_st_created, ps.updated_at as power_st_updated from hero h, power_stats ps where h.id = ? and h.power_stats_id = ps.id";
	public static final String SELECT_HERO_BY_NAME = "select h.*, ps.agility, ps.intelligence, ps.strength, ps.dexterity, ps.created_at as power_st_created, ps.updated_at as power_st_updated from hero h, power_stats ps where h.name like :name  and h.power_stats_id = ps.id";
	public static final String DELETE_HERO = "delete from hero where id = :id";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
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
			log.info("Erro ao salvar hero. ", e);
			
			throw new GeneralFailureException("Erro ao inserir hero");
		}
	}
	
	public void update(Hero hero) {
		try {
			jdbcTemplate.update(UPDATE_HERO,
					hero.getName(),
					hero.getRace(),
					hero.isEnable(),
					hero.getUpdated_at(),
					hero.getId());
		} catch (Exception e) {
			log.info("Erro ao atualizar hero. ", e);
			
			throw new GeneralFailureException("Erro ao atualizar hero");
		}
	}
	
	public Optional<Hero> findById(UUID id) {
		try {
			return Optional.of((Hero) jdbcTemplate.queryForObject(SELECT_HERO,
					new Object[]{id},
					new HeroRowMapper()));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		} catch (Exception e) {
			log.info("Erro ao consultar hero. ", e);
			
			throw new GeneralFailureException("Erro ao consultar hero by id.");
		}
	}
	
	public Optional<Hero> findByName(String name) {
		try {
			MapSqlParameterSource sqlSource = new MapSqlParameterSource();
			sqlSource.addValue("name", name);
			
			return Optional.of(namedParameterJdbcTemplate.queryForObject(SELECT_HERO_BY_NAME,
					sqlSource,
					new HeroRowMapper()));
		} catch (EmptyResultDataAccessException e) {
			log.info("Nao foram encontrados registros");
			
			return Optional.empty();
		} catch (Exception e) {
			log.info("Erro ao consultar hero by name ", e);
			
			throw new GeneralFailureException("Erro ao consultar hero pelo nome.");
		}
	}
	
	public void delete(UUID id) {
		try {
			MapSqlParameterSource sqlSource = new MapSqlParameterSource();
			sqlSource.addValue("id", id);
			
			namedParameterJdbcTemplate.update(DELETE_HERO, sqlSource);
		} catch (Exception e) {
			log.info("Erro ao deletar hero ", e);
			
			throw new GeneralFailureException("Erro ao deletar hero.");
		}
	}
}
