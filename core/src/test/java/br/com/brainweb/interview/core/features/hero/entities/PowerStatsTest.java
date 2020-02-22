package br.com.brainweb.interview.core.features.hero.entities;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.model.entities.PowerStats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("/application-it.properties")
@Transactional
public class PowerStatsTest {

    @Autowired
    private PowerStatsRepository powerStatsRepository;


    @Test
    public void shouldSavePowerStatsAndFindById() {
        // Check if it saves
        //given
        List<PowerStats> powerStats = generateListOfPowerStats(1, 1);
        PowerStats psSaved = powerStatsRepository.save(powerStats.stream().findAny().get());
        // when
        PowerStats psFound = powerStatsRepository.findById(psSaved.getId()).get();
        // then
        assertEquals(psSaved.getId(), psFound.getId());


    }

    private List<PowerStats> generateListOfPowerStats(int startInclusive, int endInclusive) {
        return IntStream.rangeClosed(startInclusive, endInclusive).
                mapToObj(n -> new PowerStats(1 + n, 2 + n, 3 + n, 4 + n))
                .collect(Collectors.toList());
    }

}
