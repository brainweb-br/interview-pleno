package br.com.brainweb.interview.core.features.hero.utils;


import br.com.brainweb.interview.core.features.hero.dto.HeroDTO;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.Race;

import java.util.Random;
import java.util.UUID;

public class Utils {

    public static Hero getHeroWithId() {
        Random r = new Random();
        return Hero.builder()
                .id(UUID.randomUUID())
                .name("Batman")
                .race(Race.HUMAN)
                .powerStats(PowerStats.builder()
                        .id(UUID.randomUUID())
                        .strength((short) r.nextInt( 100))
                        .dexterity((short) r.nextInt( 100))
                        .intelligence((short) r.nextInt( 100))
                        .agility((short) r.nextInt( 100))
                        .build())
                .enabled(true)
                .build();
    }

    public static Hero getHeroWithoutId() {
        Random r = new Random();
        return Hero.builder()
                .id(null)
                .name("Batman")
                .race(Race.HUMAN)
                .powerStats(PowerStats.builder()
                        .id(null)
                        .strength((short) r.nextInt( 100))
                        .dexterity((short) r.nextInt( 100))
                        .intelligence((short) r.nextInt( 100))
                        .agility((short) r.nextInt( 100))
                        .build())
                .enabled(true)
                .build();
    }

    public static HeroDTO getHeroDTOWithId() {
        return HeroDTO.builder()
                .id(UUID.randomUUID())
                .name("Super-man")
                .race(Race.ALIEN)
                .enabled(true)
                .powerStatsId(UUID.randomUUID())
                .agility((short) 100)
                .intelligence((short) 100)
                .strength((short) 100)
                .dexterity((short) 100)
                .build();
    }

    public static HeroDTO getHeroDTOWithoutId() {
        return HeroDTO.builder()
                .name("Super-man")
                .race(Race.ALIEN)
                .enabled(true)
                .agility((short) 100)
                .intelligence((short) 100)
                .strength((short) 100)
                .dexterity((short) 100)
                .build();
    }
}
