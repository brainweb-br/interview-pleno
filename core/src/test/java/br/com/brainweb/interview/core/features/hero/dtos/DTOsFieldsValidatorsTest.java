package br.com.brainweb.interview.core.features.hero.dtos;

import br.com.brainweb.interview.model.dtos.request.HeroRequestDTO;
import br.com.brainweb.interview.model.dtos.request.PowerStatsRequestDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static br.com.brainweb.interview.core.features.hero.TestUtils.*;
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

        PowerStatsRequestDTO powerStatsRequestDTOAgilityNull = powerStatsRequestDTOCreator();
        powerStatsRequestDTOAgilityNull.setAgility(null);

        PowerStatsRequestDTO powerStatsRequestDTODexterityNull = powerStatsRequestDTOCreator();
        powerStatsRequestDTODexterityNull.setDexterity(null);

        PowerStatsRequestDTO powerStatsRequestDTOIntelligenceNull = powerStatsRequestDTOCreator();
        powerStatsRequestDTOIntelligenceNull.setIntelligence(null);

        PowerStatsRequestDTO powerStatsRequestDTOStrengthNull = powerStatsRequestDTOCreator();
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
        HeroRequestDTO heroRequestDTONameNull = heroRequestDTOCreator();
        heroRequestDTONameNull.setName(null);

        HeroRequestDTO heroRequestDTONameLargerThan255chars = heroRequestDTOCreator();
        heroRequestDTONameLargerThan255chars.setName(generateRandomName(256));

        HeroRequestDTO heroRequestDTONameEmptyName = heroRequestDTOCreator();
        heroRequestDTONameEmptyName.setName("");

        HeroRequestDTO heroRequestDTORaceNull = heroRequestDTOCreator();
        heroRequestDTORaceNull.setRace(null);

        HeroRequestDTO heroRequestDTOPowerStatsNull = heroRequestDTOCreator();
        heroRequestDTOPowerStatsNull.setPowerStats(null);

        HeroRequestDTO heroRequestDTOEnabledNull = heroRequestDTOCreator();
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

    public PowerStatsRequestDTO powerStatsRequestDTOCreator() {

        PowerStatsRequestDTO powerStatsRequestDTO = new PowerStatsRequestDTO();
        powerStatsRequestDTO.setAgility(generateRandomNumber(1, 10));
        powerStatsRequestDTO.setDexterity(generateRandomNumber(1, 10));
        powerStatsRequestDTO.setIntelligence(generateRandomNumber(1, 10));
        powerStatsRequestDTO.setStrength(generateRandomNumber(1, 10));

        return powerStatsRequestDTO;
    }

    public HeroRequestDTO heroRequestDTOCreator() {

        HeroRequestDTO heroRequestDTO = new HeroRequestDTO();
        heroRequestDTO.setName(generateRandomName(10));
        heroRequestDTO.setRace(pickRandomRace());
        heroRequestDTO.setPowerStats(powerStatsRequestDTOCreator());
        heroRequestDTO.setEnabled(generateRandomBoolean());

        return heroRequestDTO;
    }

}
