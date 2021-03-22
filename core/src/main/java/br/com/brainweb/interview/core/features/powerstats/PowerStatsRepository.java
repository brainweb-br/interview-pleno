package br.com.brainweb.interview.core.features.powerstats;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.brainweb.interview.core.features.hero.exception.GeneralFailureException;
import br.com.brainweb.interview.model.PowerStats;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class PowerStatsRepository {
	
	public static final String UPDATE_POWER = "update power_stats set strength = ?, agility = ?, dexterity = ?, intelligence = ?, updated_at = ? where id = ?";
	public static final String INSERT_POWER = "insert into power_stats(id, strength, agility, dexterity, intelligence, created_at, updated_at) values (?, ?, ?, ?, ?, ?, ?)";
	public static final String DELETE_POWER_STATS = "delete from power_stats where id = :id";

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void save(PowerStats powerStats) {
		try {
			jdbcTemplate.update(INSERT_POWER,
					powerStats.getId(),
					powerStats.getStrength(),
					powerStats.getAgility(),
					powerStats.getDexterity(),
					powerStats.getIntelligence(),
					powerStats.getCreated_at(),
					powerStats.getUpdated_at());
		} catch (Exception e) {
			log.info("Erro ao salvar powerStats. ", e);
			
			throw new GeneralFailureException("Erro ao inserir powerStats");
		}
	}
	
	public void update(PowerStats powerStats) {
		try {
			jdbcTemplate.update(UPDATE_POWER,
					powerStats.getStrength(),
					powerStats.getAgility(),
					powerStats.getDexterity(),
					powerStats.getIntelligence(),
					powerStats.getUpdated_at(),
					powerStats.getId());
		} catch (Exception e) {
			log.info("Erro ao atualizar powerStats. ", e);
			
			throw new GeneralFailureException("Erro ao atualizar powerStats");
		}
	}
	
	public void delete(UUID id) {
		try {
			MapSqlParameterSource sqlSource = new MapSqlParameterSource();
			sqlSource.addValue("id", id);
			
			namedParameterJdbcTemplate.update(DELETE_POWER_STATS, sqlSource);
		} catch (Exception e) {
			log.info("Erro ao deletar power_stats ", e);
			
			throw new GeneralFailureException("Erro ao deletar power_stats.");
		}
	}
}
