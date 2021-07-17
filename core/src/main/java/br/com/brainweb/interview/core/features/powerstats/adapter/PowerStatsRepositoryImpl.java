package br.com.brainweb.interview.core.features.powerstats.adapter;

import br.com.brainweb.interview.core.features.hero.adapter.HeroFields;
import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.model.PowerStats;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static br.com.brainweb.interview.core.features.hero.adapter.HeroFields.CREATED_AT;
import static br.com.brainweb.interview.core.features.hero.adapter.HeroFields.ID;
import static br.com.brainweb.interview.core.features.hero.adapter.HeroFields.UPDATED_AT;
import static br.com.brainweb.interview.core.features.powerstats.adapter.PowerStatsFields.*;

@AllArgsConstructor
@Component
public class PowerStatsRepositoryImpl implements PowerStatsRepository {

    private static final String UPDATE = "update power_stats set strength = :strength, agility = :agility, dexterity = :dexterity, intelligence = :intelligence, created_at = :created_at, updated_at = :updated_at where id = :id";

    private static final String INSERT = "insert into power_stats(strength, agility, dexterity, intelligence, created_at, updated_at, id) values (:strength, :agility, :dexterity, :intelligence, :created_at, :updated_at, :id)";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<PowerStats> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void create(PowerStats powerStats) {
        jdbcTemplate.update(INSERT, toMap(powerStats));
    }

    @Override
    public void update(PowerStats powerStats) {
        jdbcTemplate.update(UPDATE, toMap(powerStats));
    }



    private Map<String, ?> toMap(PowerStats powerStats) {
        return Map.of(
                "strength", powerStats.getStrength(),
                "agility", powerStats.getAgility(),
                "dexterity", powerStats.getDexterity(),
                "intelligence", powerStats.getIntelligence(),
                "created_at", powerStats.getCreatedDt(),
                "updated_at", powerStats.getUpdatedDt(),
                "id", powerStats.getId()
        );
    }
}
