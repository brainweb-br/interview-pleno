package br.com.brainweb.interview.core.features.powerstats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.brainweb.interview.model.PowerStats;

@Repository
public class PowerStatsRepository {
	
	public static final String UPDATE_POWER = "update power_stats set strength = ?, agility = ?, dexterity = ?, intelligence = ?, updated_at = ? where id = ?";
	public static final String INSERT_POWER = "insert into power_stats(id, strength, agility, dexterity, intelligence, created_at, updated_at) values (?, ?, ?, ?, ?, ?, ?)";

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
