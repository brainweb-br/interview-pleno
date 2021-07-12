package br.com.brainweb.interview.core.features.hero.mapper;

import br.com.brainweb.interview.core.features.hero.dto.HeroDTO;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.Race;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HeroDTOMapperTest {

    private final HeroDTOMapper mapper = Mappers.getMapper(HeroDTOMapper.class);

    @Test
    void testMappertoDto() {
        Hero hero = Hero.builder()
                .id(UUID.randomUUID())
                .name("Batman")
                .race(Race.HUMAN)
                .powerStats(PowerStats.builder()
                        .id(UUID.randomUUID())
                        .agility((short) 10)
                        .intelligence((short) 100)
                        .dexterity((short) 43)
                        .strength((short) 29)
                        .build())
                .build();

        HeroDTO heroDto = mapper.toDto(hero);

        assertEquals(hero.getId(), heroDto.getId());
        assertEquals(hero.getName(), heroDto.getName());
        assertEquals(hero.getRace(), heroDto.getRace());
        assertEquals(hero.getPowerStats().getId(), heroDto.getPowerStatsId());
        assertEquals(hero.getPowerStats().getAgility(), heroDto.getAgility());
        assertEquals(hero.getPowerStats().getIntelligence(), heroDto.getIntelligence());
        assertEquals(hero.getPowerStats().getDexterity(), heroDto.getDexterity());
        assertEquals(hero.getPowerStats().getStrength(), heroDto.getStrength());
    }

    @Test
    void testMappertoModel() {
        HeroDTO heroDto = HeroDTO.builder()
                .id(UUID.randomUUID())
                .name("Batman")
                .race(Race.HUMAN)
                .powerStatsId(UUID.randomUUID())
                .agility((short) 10)
                .intelligence((short) 100)
                .dexterity((short) 43)
                .strength((short) 29)
                .build();

        Hero hero = mapper.toModel(heroDto);

        assertEquals(heroDto.getId(), hero.getId());
        assertEquals(heroDto.getName(), hero.getName());
        assertEquals(heroDto.getRace(), hero.getRace());
        assertEquals(heroDto.getPowerStatsId(), hero.getPowerStats().getId());
        assertEquals(heroDto.getAgility(), hero.getPowerStats().getAgility());
        assertEquals(heroDto.getIntelligence(), hero.getPowerStats().getIntelligence());
        assertEquals(heroDto.getDexterity(), hero.getPowerStats().getDexterity());
        assertEquals(heroDto.getStrength(), hero.getPowerStats().getStrength());
    }
}