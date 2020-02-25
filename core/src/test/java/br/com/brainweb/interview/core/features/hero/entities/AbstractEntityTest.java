package br.com.brainweb.interview.core.features.hero.entities;

import br.com.brainweb.interview.core.features.repositories.HeroRepository;
import br.com.brainweb.interview.core.features.repositories.PowerStatsRepository;
import br.com.brainweb.interview.model.entities.Hero;
import br.com.brainweb.interview.model.entities.PowerStats;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static br.com.brainweb.interview.core.features.TestUtils.createHero;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("/application-it.properties")
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AbstractEntityTest {

    private static PowerStats ps;

    private static Hero hero;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private PowerStatsRepository powerStatsRepository;

    private static final long MAX_LATENCY_MIN = 3;

    private static final String UUID_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";


    @Test
    public void timeStampTest() {
        hero = createHero();

        LocalDateTime initDateAndTime =
                Instant.ofEpochMilli(System.currentTimeMillis())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
        Hero heroSaved = heroRepository.save(hero);

        assertTrue(heroSaved.getCreated().toLocalTime()
                .isAfter(initDateAndTime.toLocalTime()));

        assertTrue(heroSaved.getCreated().toLocalTime()
                .isBefore(initDateAndTime.toLocalTime().plusMinutes(MAX_LATENCY_MIN)));
    }

    @Test
    public void uuidTest() {
        // id is UUID type
        ps = hero.getPowerStats();
        PowerStats psSaved = powerStatsRepository.save(ps);
        assertTrue(psSaved.getId()
                .toString()
                .matches(UUID_REGEX));

    }
}
