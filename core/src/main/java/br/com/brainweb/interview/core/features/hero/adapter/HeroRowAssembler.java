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
        var prefix = "";
        try {
            var powerStats = HeroRowAssembler.powerStatsRowMapper(resultSet, var2, "p_");
            return Hero.builder()
                    .id(UUID.fromString(resultSet.getString(prefix + HeroFields.ID.getBind())))
                    .name(resultSet.getString(prefix + HeroFields.NAME.getBind()))
                    .race(Race.valueOf(resultSet.getString(prefix + HeroFields.RACE.getBind())))
                    .enabled(resultSet.getBoolean(prefix + HeroFields.ENABLED.getBind()))
                    .powerStats(powerStats)
                    .createdDt(resultSet.getTimestamp(prefix + HeroFields.CREATED_AT.getBind()).toLocalDateTime())
                    .updatedDt(resultSet.getTimestamp(prefix + HeroFields.UPDATED_AT.getBind()).toLocalDateTime())
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
                    .id(UUID.fromString(resultSet.getString(prefix + PowerStatsFields.ID.getBind())))
                    .agility(resultSet.getInt(prefix + AGILITY.getBind()))
                    .dexterity(resultSet.getInt(prefix + DEXTERITY.getBind()))
                    .intelligence(resultSet.getInt(prefix + INTELLIGENCE.getBind()))
                    .strength(resultSet.getInt(prefix + STRENGTH.getBind()))
                    .createdDt(resultSet.getTimestamp(prefix + CREATED_AT.getBind()).toLocalDateTime())
                    .updatedDt(resultSet.getTimestamp(prefix + UPDATED_AT.getBind()).toLocalDateTime())
                    .build();
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

}
