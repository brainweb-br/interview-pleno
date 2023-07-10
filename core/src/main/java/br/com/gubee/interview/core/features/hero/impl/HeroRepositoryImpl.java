package br.com.gubee.interview.core.features.hero.impl;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.dto.HeroResponse;
import br.com.gubee.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Repository
public class HeroRepositoryImpl implements HeroRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public HeroResponse getById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT " +
                        "h.name, h.race, h.enabled, ps.strength, ps.agility, ps.dexterity, ps.intelligence " +
                        "FROM hero h " +
                        "INNER JOIN power_stats ps ON h.power_stats_id = ps.id " +
                        "WHERE h.id = ?",
                    new Object[]{id},
                    (rs, rowNUm) -> getHeroAndPowerStatsByQuery(rs));
        } catch (Exception e){
            return null;
        }

    }

    @Override
    public HeroResponse getByName(String name) {
        try{
            return jdbcTemplate.queryForObject(
                    "SELECT " +
                            "h.name, h.race, h.enabled, ps.strength, ps.agility, ps.dexterity, ps.intelligence " +
                            "FROM hero h " +
                            "INNER JOIN power_stats ps ON h.power_stats_id = ps.id " +
                            "WHERE h.name = ?",
                    new Object[]{name},
                    (rs, rowNUm) -> getHeroAndPowerStatsByQuery(rs));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UUID getPowerStatsUuidById(UUID uuid) {
        try{
            return jdbcTemplate.queryForObject(
                    "SELECT " +
                        "h.power_stats_id " +
                        "FROM hero h " +
                        "INNER JOIN power_stats ps ON h.power_stats_id = ps.id " +
                        "WHERE h.id = ?",
                    new Object[]{uuid},
                    UUID.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int save(HeroResponse hero, UUID uuidPowerStats) {
            return jdbcTemplate.update(
                    "INSERT INTO interview_service.hero " +
                        "(id, \"name\", race, power_stats_id) " +
                        "VALUES(interview_service.uuid_generate_v4(), ?, ?, ?)",
                    hero.getName(), hero.getRace(), uuidPowerStats
            );
    }

    @Override
    public int deleteById(UUID id) {
            return jdbcTemplate.update(
                    "DELETE FROM interview_service.hero " +
                            "WHERE id = ? ",
                    id);
    }

    @Override
    public int editHero(HeroResponse hero, UUID id) {
            return jdbcTemplate.update(
                    "UPDATE interview_service.hero " +
                        "SET \"name\"= ? , race= ?, enabled= ?, updated_at= now() " +
                        "WHERE id= ? ",
                    hero.getName(), hero.getRace(), hero.isEnabled(), id
            );
    }

    private HeroResponse getHeroAndPowerStatsByQuery(ResultSet rs) throws SQLException {
        return new HeroResponse(
                rs.getString("name"),
                rs.getString("race"),
                rs.getBoolean("enabled"),
                rs.getInt("strength"),
                rs.getInt("agility"),
                rs.getInt("dexterity"),
                rs.getInt("intelligence")
        );
    }
}
