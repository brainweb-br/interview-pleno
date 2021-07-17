package br.com.brainweb.interview.core.features.hero.adapter;

import br.com.brainweb.interview.core.features.hero.HeroRepository;
import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.model.Hero;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

import static br.com.brainweb.interview.core.features.hero.adapter.HeroFields.*;

@Component
@AllArgsConstructor
public class HeroRepositoryImpl implements HeroRepository {

    private final PowerStatsRepository powerStatsRepository;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final String INSERT = "insert into hero(name, race, power_stats_id, enabled, created_at, updated_at, id) values (:name, :race, :power_stats_id, :enabled, :created_at, :updated_at, :id)";

    private static final String UPDATE = "update hero set name = :name, race = :race, enabled = :enabled, created_at = :created_at, updated_at = :updated_at where id = :id";

    public static final String SELECT_ID = "select hero.*, power_stats.* from hero, power_stats where hero.id = :id and hero.power_stats_id = power_stats.id";


    @Override
    public Optional<Hero> findById(String id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_ID, Map.of("id", id), HeroRowAssembler::heroRowMapper));
        }catch (EmptyResultDataAccessException ex){
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void create(Hero hero) {
        powerStatsRepository.create(hero.getPowerStats());
        jdbcTemplate.update(INSERT, toMap(hero));
    }

    @Override
    public void update(Hero hero) {
        powerStatsRepository.update(hero.getPowerStats());
        jdbcTemplate.update(UPDATE, toMap(hero));
    }


    private Map<String, ?> toMap(Hero hero) {
        return Map.of(
                NAME.getBind(), hero.getName(),
                RACE.getBind(), hero.getRace().name(),
                POWER_STATS_ID.getBind(), hero.getPowerStatsId(),
                ENABLED.getBind(), hero.isEnabled(),
                CREATED_AT.getBind(), hero.getCreatedDt(),
                UPDATED_AT.getBind(), hero.getUpdatedDt(),
                ID.getBind(), hero.getId()
        );
    }

}
