package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.enums.RaceType;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;

public class Utils {

    public static Hero getValidHero() {
        Random r = new Random();
        PowerStats power = new PowerStats(UUID.randomUUID(), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100),
                Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        Hero hero = new Hero(UUID.randomUUID(), "teste",
                RaceType.HUMAN, true, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), power);
        return hero;
    }

    public static PowerStats generateRandomPower() {
        Random r = new Random();
        return new PowerStats(UUID.randomUUID(), r.nextInt(100), r.nextInt(100),
                r.nextInt(100), r.nextInt(100),Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
    }
}
