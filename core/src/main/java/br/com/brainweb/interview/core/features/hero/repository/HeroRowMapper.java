package br.com.brainweb.interview.core.features.hero.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;

import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;

public class HeroRowMapper implements RowMapper<Hero> {
	
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String RACE = "RACE";
	private static final String ENABLED = "enabled";
	private static final String CREATED_DATE = "created_at";
	private static final String UPDATED_DATE = "updated_at";
	
	private static final String POWER_STATS_ID = "power_stats_id";
	private static final String AGILITY = "agility";
	private static final String INTELLIGENCE = "intelligence";
	private static final String STRENGTH = "strength";
	private static final String DEXTERITY = "dexterity";
	private static final String POWER_ST_CREATED_DATE = "power_st_created_at";
	private static final String POWER_ST_UPDATED_DATE = "power_st_updated_at";
	
	@Override
	public Hero mapRow(ResultSet rs, int rowNum) throws SQLException {
		String id = rs.getString(ID);
		String name = rs.getString(NAME);
		String race = rs.getString(RACE);
		boolean enabled = rs.getBoolean(ENABLED);
		
		String powerStatsId = rs.getString(POWER_STATS_ID);
		int agility = rs.getInt(AGILITY);
		int intelligence = rs.getInt(INTELLIGENCE);
		int strength = rs.getInt(STRENGTH);
		int dexterity = rs.getInt(DEXTERITY);
		
		PowerStats powerStats = PowerStats.builder()
				.id(UUID.fromString(powerStatsId))
				.agility(agility)
				.intelligence(intelligence)
				.strength(strength)
				.dexterity(dexterity)
				.build();
		
		Hero hero = Hero.builder()
				.id(UUID.fromString(id))
				.name(name)
				.race(race)
				.enable(enabled)
				.powerStats(powerStats)
				.build();
		return hero;
	}
}
