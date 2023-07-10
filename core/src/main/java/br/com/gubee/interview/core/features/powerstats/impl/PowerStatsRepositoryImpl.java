package br.com.gubee.interview.core.features.powerstats.impl;

import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.dto.HeroResponse;
import br.com.gubee.interview.dto.PowerStatsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.UUID;

@Repository
public class PowerStatsRepositoryImpl implements PowerStatsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UUID save(PowerStatsResponse powerStatsResponse) {
        return jdbcTemplate.queryForObject(
                "INSERT INTO interview_service.power_stats " +
                "(id, strength, agility, dexterity, intelligence) " +
                "VALUES(interview_service.uuid_generate_v4(), ?, ?, ?, ?) " +
                "RETURNING ID",
                new Object[]{powerStatsResponse.getStrength(), powerStatsResponse.getAgility(),
                powerStatsResponse.getDexterity(), powerStatsResponse.getIntelligence()},
                UUID.class);
    }

    @Override
    public int deleteById(UUID id) {
        return jdbcTemplate.update(
                "DELETE FROM interview_service.power_stats " +
                    "WHERE id = ? ",
                id);
    }

    @Override
    public int editHero(HeroResponse heroResponse, UUID id) {
        return jdbcTemplate.update(
                "UPDATE interview_service.power_stats " +
                    "SET strength= ?, agility= ?, dexterity= ?, intelligence= ? , created_at=now() " +
                    "WHERE id = ? ",
                heroResponse.getStrength(), heroResponse.getAgility(), heroResponse.getDexterity(),
                heroResponse.getIntelligence(), id);
    }
}
