package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.model.PowerStats;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PowerStatsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Optional<PowerStats> findById(UUID id) {
        return Optional.of(jdbcTemplate.queryForObject(
                " select * from power_stats where id = ?",
                new Object[]{id},
                this::mapPowerSTATS
        ));
    }

    public String saveAndUpdate(PowerStats powerStats) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String INSERT_SQL = "insert into power_stats (" +
                "strength," +
                "agility," +
                "dexterity," +
                "intelligence," +
                "created_at," +
                "updated_at) " +
                "values (?,?,?,?,?,?)";

        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
                System.out.println("powerStats.getCreatedAt() = " + powerStats.getCreatedAt());
                ps.setInt(1, powerStats.getStrength());
                ps.setInt(2, powerStats.getAgility());
                ps.setInt(3, powerStats.getIntelligence());
                ps.setInt(4, powerStats.getDexterity());
                ps.setObject(5, powerStats.getCreatedAt());
                ps.setObject(6, powerStats.getUpdatedAt());

                return ps;
            }
        }, keyHolder);

        System.out.println("keyHolder = " + keyHolder);

        return String.valueOf(keyHolder.getKeyList().get(0).get("id"));

    }

    public int delete(UUID id) {
        return jdbcTemplate.update(
                "delete power_stats where id = ?",
                id);
    }


    @SneakyThrows
    private PowerStats mapPowerSTATS(ResultSet resultSet, int i) {
        return new PowerStats(
                resultSet.getObject("id", UUID.class),
                resultSet.getInt("strength"),
                resultSet.getInt("agility"),
                resultSet.getInt("dexterity"),
                resultSet.getInt("intelligence"),
                resultSet.getObject("created_at", OffsetDateTime.class),
                resultSet.getObject("updated_at", OffsetDateTime.class)

        );
    }
}
