package br.com.brainweb.interview.core.features.hero.adapter;

import br.com.brainweb.interview.core.features.hero.HeroRepository;
import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.model.Hero;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static br.com.brainweb.interview.core.features.hero.adapter.HeroFields.*;

@Component
@AllArgsConstructor
public class HeroRepositoryImpl implements HeroRepository {

    private final PowerStatsRepository powerStatsRepository;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final String INSERT = "insert into hero(name, race, power_stats_id, enabled, created_at, updated_at, id) values (:name, :race, :power_stats_id, :enabled, :created_at, :updated_at, :id)";

    private static final String UPDATE = "update hero set name = :name, race = :race, enabled = :enabled, created_at = :created_at, updated_at = :updated_at where id = :id";

    public static final String SELECT_ID = "select hero.*, p.strength as p_strength, p.agility as p_agility, p.dexterity as p_dexterity, p.intelligence as p_intelligence, p.created_at as p_created_at, p.updated_at as p_updated_at, p.id as p_id from hero inner join power_stats p on p.id = hero.power_stats_id where hero.id = :id";

    public static final String SELECT_LIKE_NAME = "select hero.*, p.strength as p_strength, p.agility as p_agility, p.dexterity as p_dexterity, p.intelligence as p_intelligence, p.created_at as p_created_at, p.updated_at as p_updated_at, p.id as p_id from hero inner join power_stats p on p.id = hero.power_stats_id where hero.name like :name";

    public static final String SELECT_NAME = "select hero.*, p.strength as p_strength, p.agility as p_agility, p.dexterity as p_dexterity, p.intelligence as p_intelligence, p.created_at as p_created_at, p.updated_at as p_updated_at, p.id as p_id from hero inner join power_stats p on p.id = hero.power_stats_id where hero.name = :name";

    private static final String DELETE = "delete from hero WHERE hero.id = :id";

    private static final String DELETE_ALL = "delete from hero";

    @Override
    @Cacheable(cacheNames = "hero", key="#id")
    public Optional<Hero> findById(UUID id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_ID, Map.of(ID.getBind(), id), HeroRowAssembler::heroRowMapper));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    @Cacheable(cacheNames = "hero", key="#name")
    public Optional<Hero> findByName(String name) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_NAME, Map.of(NAME.getBind(), name), HeroRowAssembler::heroRowMapper));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    @Cacheable(cacheNames = "hero", key="#text")
    public List<Hero> search(String text) {
        return jdbcTemplate.query(SELECT_LIKE_NAME, Map.of(NAME.getBind(), "%" + text + "%"), HeroRowAssembler::heroRowMapper);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "hero", allEntries = true)
    public void create(Hero hero) {
        powerStatsRepository.create(hero.getPowerStats());
        jdbcTemplate.update(INSERT, toMap(hero));
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "hero", allEntries = true)
    public void update(Hero hero) {
        powerStatsRepository.update(hero.getPowerStats());
        jdbcTemplate.update(UPDATE, toMap(hero));
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "hero", allEntries = true)
    public void delete(Hero hero) {
        jdbcTemplate.update(DELETE, Map.of(ID.getBind(), hero.getId()));
        powerStatsRepository.delete(hero.getPowerStats());
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "hero", allEntries = true)
    public void deleteAll() {
        jdbcTemplate.update(DELETE_ALL, Map.of());
        powerStatsRepository.deleteAll();
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
