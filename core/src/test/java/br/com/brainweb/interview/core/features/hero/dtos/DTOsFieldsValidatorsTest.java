package br.com.brainweb.interview.core.features.hero.dtos;

import br.com.brainweb.interview.model.dtos.request.HeroRequestDTO;
import br.com.brainweb.interview.model.dtos.request.PowerStatsRequestDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static br.com.brainweb.interview.core.features.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DTOsFieldsValidatorsTest {

    static private Validator validator;


    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testPowerStatsRequestDTOValidators() {

        PowerStatsRequestDTO powerStatsRequestDTOAgilityNull = createPowerStatsRequestDTO();
        powerStatsRequestDTOAgilityNull.setAgility(null);

        PowerStatsRequestDTO powerStatsRequestDTODexterityNull = createPowerStatsRequestDTO();
        powerStatsRequestDTODexterityNull.setDexterity(null);

        PowerStatsRequestDTO powerStatsRequestDTOIntelligenceNull = createPowerStatsRequestDTO();
        powerStatsRequestDTOIntelligenceNull.setIntelligence(null);

        PowerStatsRequestDTO powerStatsRequestDTOStrengthNull = createPowerStatsRequestDTO();
        powerStatsRequestDTOStrengthNull.setStrength(null);

        assertAll("Validation ofPowerStatsRequestDTO has failed ",
                () -> assertEquals("agility may not be null", getMessage(powerStatsRequestDTOAgilityNull)),
                () -> assertEquals("dexterity may not be null", getMessage(powerStatsRequestDTODexterityNull)),
                () -> assertEquals("intelligence may not be null", getMessage(powerStatsRequestDTOIntelligenceNull)),
                () -> assertEquals("strength may not be null", getMessage(powerStatsRequestDTOStrengthNull))
        );


    }

    @Test
    public void testHeroRequestDTOValidators() {
        HeroRequestDTO heroRequestDTONameNull = createHeroRequestDTO();
        heroRequestDTONameNull.setName(null);

        HeroRequestDTO heroRequestDTONameLargerThan255chars = createHeroRequestDTO();
        heroRequestDTONameLargerThan255chars.setName(generateRandomName(256));

        HeroRequestDTO heroRequestDTONameEmptyName = createHeroRequestDTO();
        heroRequestDTONameEmptyName.setName("");

        HeroRequestDTO heroRequestDTORaceNull = createHeroRequestDTO();
        heroRequestDTORaceNull.setRace(null);

        HeroRequestDTO heroRequestDTOPowerStatsNull = createHeroRequestDTO();
        heroRequestDTOPowerStatsNull.setPowerStats(null);

        HeroRequestDTO heroRequestDTOEnabledNull = createHeroRequestDTO();
        heroRequestDTOEnabledNull.setEnabled(null);


        assertAll("Validation of HeroRequestDTO has failed ",
                () -> assertEquals("Name may not be blank, empty or null", getMessage(heroRequestDTONameNull)),
                () -> assertEquals("Name can't be longer then 255 char", getMessage(heroRequestDTONameLargerThan255chars)),
                () -> assertEquals("Name may not be blank, empty or null", getMessage(heroRequestDTONameEmptyName)),
                () -> assertEquals("Race may not be null", getMessage(heroRequestDTORaceNull)),
                () -> assertEquals("Power stats id may not be null", getMessage(heroRequestDTOPowerStatsNull)),
                () -> assertEquals("Enabled may not be null", getMessage(heroRequestDTOEnabledNull))
        );

    }

    private String getMessage(PowerStatsRequestDTO powerStatsRequestDTOWithNullField) {

        return validator.validate(powerStatsRequestDTOWithNullField).iterator().next().getMessage();
    }

    private String getMessage(HeroRequestDTO heroRequestDTOWithNullField) {

        return validator.validate(heroRequestDTOWithNullField).iterator().next().getMessage();
    }


}
