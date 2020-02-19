package br.com.brainweb.interview.core.features.hero;

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
public class HeroRepository {

    // busca por ID
    // busca por nome
    //atualizar todos os campos
    // excluzao


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<Hero> findHeros() {
        return jdbcTemplate.query(
                "select id, name, race, power_stats_id, enabled, createdAt, updated_at",
                this::mapHero
        );
    }

    public Hero findHeroById(Integer id) {
        return jdbcTemplate.queryForObject(
                "select id, name, race, power_stats_id, enabled, createdAt, updated_at where id = ?",
                new Object[]{id},
                this::mapHero
        );
    }

    public int removeHeroById(Integer id) {
        return jdbcTemplate.update(
                "delete from hero where id = ?", id);
    }

    public void insertUpdatePowerStats(PowerStats powerStats) {

        Object[] params = new Object[]{
                powerStats.getStrength(),
                powerStats.getDexterity(),
                powerStats.getIntelligence(),
                powerStats.getCreatedAt(),
                powerStats.getUpdatedAt()

        };
        int[] types = new int[]{
                Types.INTEGER,// strength
                Types.INTEGER,// dexterity
                Types.INTEGER,//intelligence
                Types.DATE,
                Types.DATE
        };


        int update = jdbcTemplate.update("insert into power_stats values(?,?,?,?,?,?)", params, types);
    }

    @SneakyThrows
    private Hero mapHero(ResultSet resultSet, int i) {
        return new Hero(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("race"),
                resultSet.getLong("power_stats_id"),
                resultSet.getBoolean("enabled"),
                resultSet.getObject("createdAt", LocalDateTime.class),
                resultSet.getObject("updated_at", LocalDateTime.class)
        );
    }
}
