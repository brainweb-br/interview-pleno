package br.com.brainweb.interview.core.features.hero;


import br.com.brainweb.interview.core.features.powerstats.PowerStatsService;
import br.com.brainweb.interview.model.PowerStats;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
public class PowerStatsServiceTest {

    @InjectMocks
    PowerStatsService powerStatsService;

    @Test
    void shouldUpdatePowerAttributesExceptNullFields() {
        PowerStats powerStats = Utils.generateRandomPower();
        PowerStats newValues = Utils.generateRandomPower();
        newValues.setIntelligence(null);

        powerStatsService.updatePowerAttributes(powerStats, newValues);

        assertEquals(powerStats.getStrength(), newValues.getStrength());
        assertEquals(powerStats.getAgility(), newValues.getAgility());
        assertEquals(powerStats.getDexterity(), newValues.getDexterity());

        // should not update null fields
        assertNotEquals(powerStats.getIntelligence(), newValues.getIntelligence());
    }

    @Test
    void shouldComparePowerOfTwoHeroes() {
        PowerStats powerStatsOne = Utils.generateRandomPower();
        PowerStats powerStatsTwo = Utils.generateRandomPower();

        PowerStats compareResult = powerStatsService.compare(powerStatsOne, powerStatsTwo);

        assertEquals(powerStatsOne.getStrength() - powerStatsTwo.getStrength(), compareResult.getStrength());
        assertEquals(powerStatsOne.getAgility() - powerStatsTwo.getAgility(), compareResult.getAgility());
        assertEquals(powerStatsOne.getDexterity() - powerStatsTwo.getDexterity(), compareResult.getDexterity());
        assertEquals(powerStatsOne.getIntelligence() - powerStatsTwo.getIntelligence(), compareResult.getIntelligence());
    }


}
