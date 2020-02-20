package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.RepositoryGen;
import br.com.brainweb.interview.model.Hero;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class HeroRepository implements RepositoryGen<Hero> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate
                .queryForObject("select count(*) from hero", Integer.class);
    }

    @Override
    public int saveAndUpdate(Hero h) {
        return jdbcTemplate.update(
                "insert into hero (" +
                        "name," +
                        "race," +
                        "power_stats_id," +
                        "enabled," +
                        "created_at," +
                        "updated_at) " +
                        "values (?,?,?,?,?,?)",
                h.getName(),
                h.getRace(),
                h.getPowerStatsId(),
                h.isEnabled(),
                h.getCreatedAt(),
                h.getUpdatedAt());
    }


    @Override
    public int deleteById(UUID id) {
        return jdbcTemplate.update(
                "delete from hero where id = ?",
                id);
    }

    @Override
    public List<Hero> findAll() {
        return jdbcTemplate.query(
                "select * from hero",
                this::mapHero
        );
    }

    @Override
    public List<Hero> findByName(String name) {
        return jdbcTemplate.query(
                "select * from hero where name like ?",
                new Object[]{"%" + name + "%"}, this::mapHero
        );
    }

    @Override
    public Optional<Hero> findById(UUID id) {
        return Optional.of(jdbcTemplate.queryForObject(
                "select * where id = ?",
                new Object[]{id},
                this::mapHero
        ));
    }

    @SneakyThrows
    private Hero mapHero(ResultSet resultSet, int i) {
        return new Hero(
                resultSet.getObject("id", UUID.class),
                resultSet.getString("name"),
                resultSet.getString("race"),
                resultSet.getObject("power_stats_id", UUID.class),
                resultSet.getBoolean("enabled"),
                resultSet.getObject("created_at", OffsetDateTime.class),
                resultSet.getObject("updated_at", OffsetDateTime.class)
        );
    }
}
