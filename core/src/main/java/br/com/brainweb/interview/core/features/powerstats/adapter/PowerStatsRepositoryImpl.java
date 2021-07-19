package br.com.brainweb.interview.core.features.powerstats.adapter;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.model.PowerStats;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

import static br.com.brainweb.interview.core.features.hero.adapter.HeroFields.CREATED_AT;
import static br.com.brainweb.interview.core.features.hero.adapter.HeroFields.ID;
import static br.com.brainweb.interview.core.features.hero.adapter.HeroFields.UPDATED_AT;
import static br.com.brainweb.interview.core.features.powerstats.adapter.PowerStatsFields.*;

@AllArgsConstructor
@Component
public class PowerStatsRepositoryImpl implements PowerStatsRepository {

    private static final String UPDATE = "update power_stats set strength = :strength, agility = :agility, dexterity = :dexterity, intelligence = :intelligence, created_at = :created_at, updated_at = :updated_at where id = :id";

    private static final String INSERT = "insert into power_stats(strength, agility, dexterity, intelligence, created_at, updated_at, id) values (:strength, :agility, :dexterity, :intelligence, :created_at, :updated_at, :id)";

    private static final String DELETE = "delete from power_stats WHERE power_stats.id = :id";

    private static final String DELETE_ALL = "delete from power_stats";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void create(PowerStats powerStats) {
        jdbcTemplate.update(INSERT, toMap(powerStats));
    }

    @Override
    public void update(PowerStats powerStats) {
        jdbcTemplate.update(UPDATE, toMap(powerStats));
    }

    @Override
    public void delete(PowerStats powerStats) {
        jdbcTemplate.update(DELETE, Map.of(ID.getBind(), powerStats.getId()));
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(DELETE_ALL, Map.of());
    }

    private Map<String, ?> toMap(PowerStats powerStats) {
        return Map.of(
                STRENGTH.getBind(), powerStats.getStrength(),
                AGILITY.getBind(), powerStats.getAgility(),
                DEXTERITY.getBind(), powerStats.getDexterity(),
                INTELLIGENCE.getBind(), powerStats.getIntelligence(),
                CREATED_AT.getBind(), powerStats.getCreatedDt(),
                UPDATED_AT.getBind(), powerStats.getUpdatedDt(),
                ID.getBind(), powerStats.getId()
        );
    }
}
