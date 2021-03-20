package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.dtos.HeroDto;
import br.com.brainweb.interview.core.dtos.HeroDto.HeroOut;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.Hero.Fields;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HeroRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;


    public Boolean validateUnique(final String name) {
        return namedJdbcTemplate
                .queryForObject(
                        "select exists ( select from hero where name =:name)",
                        new MapSqlParameterSource()
                                .addValue(Fields.name.name(), name, Types.VARCHAR),
                        Boolean.class
                );
    }

    public UUID create(final Hero hero) {
        return namedJdbcTemplate.queryForObject(
                "INSERT INTO interview_service.hero "
                        + "(name, race, power_stats_id) "
                        + " VALUES(:name, :race, :power) returning id ",
                new MapSqlParameterSource()
                        .addValue(Fields.name.name(), hero.getName())
                        .addValue(Fields.race.name(), hero.getRace())
                        .addValue(Fields.power.name(), hero.getPower()), UUID.class);

    }

    public Optional<HeroOut> findById(final UUID id) {
        List<HeroOut> result = getQueryParam(null, id);
        return Optional.ofNullable(DataAccessUtils.singleResult(result));
    }

    public List<HeroOut> findByName(final String name) {
        return getQueryParam( name, null);
    }


    public void delete(final UUID id) {
         namedJdbcTemplate.update(
                "delete from hero where id = :id",
                new MapSqlParameterSource()
                        .addValue(Fields.id.name(), id)
        );
    }

    public Integer update(final UUID id, final Hero input) {

        return namedJdbcTemplate.update(
                "UPDATE interview_service.hero SET name =:name, race=:race, power_stats_id=:power, enabled=enabled, updated_at= now() WHERE id = :id",
                new MapSqlParameterSource()
                        .addValue(Fields.id.name(), id)
                        .addValue(Fields.name.name(), input.getName())
                        .addValue(Fields.power.name(), input.getPower())
                        .addValue(Fields.enabled.name(), input.getEnabled())
                        .addValue(Fields.race.name(), input.getRace())
        );
    }

    public List<HeroOut> listComparator(final List<String> names) {
        return namedJdbcTemplate
                .query(this.getQueryComparator(),
                        new MapSqlParameterSource()
                                .addValue(Fields.name.name(), names),
                        new HeroDtoMapper());
    }

    private class HeroDtoMapper implements RowMapper<HeroOut> {

        @Override
        public HeroOut mapRow(ResultSet rs, int rowNum) throws SQLException {

            return HeroOut.builder()
                    .id(rs.getObject(Fields.id.name(), UUID.class))
                    .powerStatsId(rs.getObject(Fields.power.name(), UUID.class))
                    .name(rs.getString(Fields.name.name()))
                    .race(rs.getString(Fields.race.name()))
                    .enabled(rs.getBoolean(Fields.enabled.name()))
                    .createdAt(rs.getObject(Fields.createdAt.name(), OffsetDateTime.class))
                    .updatedAt(rs.getObject(Fields.updatedAt.name(), OffsetDateTime.class))
                    .agility(rs.getLong(HeroOut.Fields.agility.name()))
                    .dexterity(rs.getLong(HeroOut.Fields.dexterity.name()))
                    .intelligence(rs.getLong(HeroOut.Fields.intelligence.name()))
                    .strength(rs.getLong(HeroOut.Fields.strength.name()))
                    .build();
        }
    }

    private List<HeroOut> getQueryParam(String name, UUID id) {
        return namedJdbcTemplate
                .query(this.getQueryHero(),
                        new MapSqlParameterSource()
                                .addValue(Fields.name.name(), name != null ? "%"+name+"%" : null, Types.VARCHAR)
                                .addValue(Fields.id.name(), id, Types.VARCHAR),
                        new HeroDtoMapper());
    }

    private String getQueryHero() {

        return "select h.id, h.name, h.race, h.enabled, h.created_at as createdAt, h.updated_at as updatedAt  "
                + " ,h.power_stats_id as power, pw.agility, pw.dexterity, pw.intelligence, pw.strength "
                + " from hero h inner join power_stats pw on h.power_stats_id = pw.id "
                + " where 1=1  "
                + " and (:id is null or h.id = :id::uuid) "
                + " and (:name is null or h.name like :name ) ";
    }

    private String getQueryComparator() {
        return "select h.id, h.name, h.race, h.enabled, h.created_at as createdAt, h.updated_at as updatedAt  "
                + " ,h.power_stats_id as power, pw.agility, pw.dexterity, pw.intelligence, pw.strength "
                + " from hero h inner join power_stats pw on h.power_stats_id = pw.id "
                + " where name in ( :name )";
    }
}
