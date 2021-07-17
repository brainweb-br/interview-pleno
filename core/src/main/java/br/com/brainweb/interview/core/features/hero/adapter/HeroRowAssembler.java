package br.com.brainweb.interview.core.features.hero.adapter;

import br.com.brainweb.interview.core.features.powerstats.adapter.PowerStatsFields;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.Race;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

import static br.com.brainweb.interview.core.features.hero.adapter.HeroFields.UPDATED_AT;
import static br.com.brainweb.interview.core.features.powerstats.adapter.PowerStatsFields.*;

public class HeroRowAssembler {

    public static Hero heroRowMapper(ResultSet resultSet, int var2) {
        var prefix = "hero.";
        try {
            var powerStats = HeroRowAssembler.powerStatsRowMapper(resultSet, var2, ".power_stats");
            return Hero.builder()
                    .id(UUID.fromString(resultSet.getString(prefix + HeroFields.ID)))
                    .name(resultSet.getString(prefix + HeroFields.NAME))
                    .race(Race.valueOf(resultSet.getString(prefix + HeroFields.RACE)))
                    .enabled(resultSet.getBoolean(prefix + HeroFields.ENABLED))
                    .powerStats(powerStats)
                    .createdDt(resultSet.getObject(prefix + HeroFields.CREATED_AT, LocalDateTime.class))
                    .updatedDt(resultSet.getObject(prefix + HeroFields.UPDATED_AT, LocalDateTime.class))
                    .build();
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    public static PowerStats powerStatsRowMapper(ResultSet resultSet,
                                                 int var2,
                                                 String prefix) {
        try {
            return PowerStats.builder()
                    .id(UUID.fromString(resultSet.getString(prefix + PowerStatsFields.ID)))
                    .agility(resultSet.getInt(prefix + AGILITY))
                    .dexterity(resultSet.getInt(prefix + DEXTERITY))
                    .intelligence(resultSet.getInt(prefix + INTELLIGENCE))
                    .strength(resultSet.getInt(prefix + STRENGTH))
                    .createdDt(resultSet.getObject(prefix + CREATED_AT, LocalDateTime.class))
                    .updatedDt(resultSet.getObject(prefix + UPDATED_AT, LocalDateTime.class))
                    .build();
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

}
