package br.com.brainweb.interview.core.features.powerstats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.brainweb.interview.model.PowerStats;

@Repository
public class PowerStatsRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void save(PowerStats powerStats) {
		String insertPowerStat = "insert into power_stats(id, strength, agility, dexterity, intelligence, created_at, updated_at) values (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			jdbcTemplate.update(insertPowerStat,
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
}
