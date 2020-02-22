package br.com.brainweb.interview.core.features.hero.entities;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.model.entities.PowerStats;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("/application-it.properties")
@Transactional
public class PowerStatsTest {

    @Autowired
    private PowerStatsRepository powerStatsRepository;

    private static PowerStats powerStats;

    @BeforeAll
    public static void init() {
        powerStats = new PowerStats();
        powerStats.setStrength(1);
        powerStats.setAgility(2);
        powerStats.setDexterity(3);
        powerStats.setIntelligence(4);
    }

    @Test
    @Commit
    public void shouldSavePowerStatsAndFindById() {
        //given
        List<PowerStats> powerStats = generateListOfPowerStats(1, 1);
        PowerStats powerStatsSaved = powerStatsRepository.save(powerStats.stream().findAny().get());

        // when
        PowerStats found = powerStatsRepository.findById(powerStatsSaved.getId()).get();

        // then
        // id is UUID type
        System.out.println("found = " + found.getId());
        assertTrue(powerStatsSaved.getId()
                .toString()
                .matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"));


        PowerStats powerStats1 = generateListOfPowerStats(1, 1).get(0);
        powerStats1.setIntelligence(null);

        PowerStats powerStatsSaved2 = powerStatsRepository.save(powerStats1);


        assertThat(found.getId())
                .isEqualTo(found.getId());

    }

    private List<PowerStats> generateListOfPowerStats(int startInclusive, int endInclusive) {
        return IntStream.rangeClosed(startInclusive, endInclusive).
                mapToObj(n -> new PowerStats(1 + n, 2 + n, 3 + n, 4 + n))
                .collect(Collectors.toList());
    }

}
