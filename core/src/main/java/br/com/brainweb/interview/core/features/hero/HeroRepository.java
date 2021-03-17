package br.com.brainweb.interview.core.features.hero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;

@Repository
public class HeroRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void save(Hero hero) {
		String insertHero = "insert into hero(id, name, race, power_stats_id, enabled, created_at, updated_at) values (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PowerStats powerStats = hero.getPowerStats();
			
			jdbcTemplate.update(insertHero,
					hero.getId(),
					hero.getName(),
					hero.getRace(),
					powerStats.getId(),
					hero.isEnable(),
					hero.getCreated_at(),
					hero.getUpdated_at());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
