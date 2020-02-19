package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PowerStatsRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<PowerStats> findPowerStats() {
        return jdbcTemplate.query(
                " select id, strength, dexterity, intelligence, createdAt, updated_at",
                this::mapPowerSTATS
        );
    }

    public PowerStats findPowerStatsById(Integer id) {
        return jdbcTemplate.queryForObject(
                " select id, strength, dexterity, intelligence, createdAt, updated_at where id = ?",
                new Object[]{id},
                this::mapPowerSTATS
        );
    }




    public void insertUpdateHero(Hero hero) {

        Object[] params = new Object[]{
                hero.getName(), hero.getRace(), hero.getPowerStatsId(), hero.isEnabled(), hero.getCreatedAt(), hero.getUpdatedAt()
        };
        int[] types = new int[]{
                Types.VARCHAR,// name
                Types.VARCHAR,// race
                Types.INTEGER,//statsId
                Types.BOOLEAN, // enabled
                Types.DATE,
                Types.DATE
        };


        int update = jdbcTemplate.update("insert into hero values(?,?,?,?,?,?)", params, types);
    }

    @SneakyThrows
    private PowerStats mapPowerSTATS(ResultSet resultSet, int i) {
        return new PowerStats(
                resultSet.getLong("id"),
                resultSet.getInt("strength"),
                resultSet.getInt("dexterity"),
                resultSet.getInt("intelligence"),
                resultSet.getObject("createdAt", LocalDateTime.class),
                resultSet.getObject("updated_at", LocalDateTime.class)

        );
    }


}
