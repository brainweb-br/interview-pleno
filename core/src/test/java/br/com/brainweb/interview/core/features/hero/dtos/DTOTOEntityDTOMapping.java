package br.com.brainweb.interview.core.features.hero.dtos;

import br.com.brainweb.interview.model.dtos.request.HeroRequestDTO;
import br.com.brainweb.interview.model.dtos.request.PowerStatsRequestDTO;
import br.com.brainweb.interview.model.dtos.response.HeroResponseDTO;
import br.com.brainweb.interview.model.dtos.response.PowerStatsResponseDTO;
import br.com.brainweb.interview.model.entities.Hero;
import br.com.brainweb.interview.model.entities.PowerStats;
import br.com.brainweb.interview.model.enums.Race;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DTOTOEntityDTOMapping {

    private static ModelMapper modelMapper;
    private static PowerStats powerStats;
    private static PowerStatsResponseDTO powerStatsResponseDTO;
    private static Hero heroEntity;
    private static HeroResponseDTO heroResponseDTO;
    private static PowerStatsRequestDTO powerStatsRequestDTO;

    @BeforeAll
    private static void setUp() {
        modelMapper = new ModelMapper();

        powerStats = new PowerStats();
        powerStats.setId(UUID.randomUUID());
        powerStats.setAgility(generateRandomNumber(1, 10));
        powerStats.setDexterity(generateRandomNumber(1, 10));
        powerStats.setIntelligence(generateRandomNumber(1, 10));
        powerStats.setStrength(generateRandomNumber(1, 10));
        powerStats.setCreated(LocalDateTime.now());
        powerStats.setUpdated(LocalDateTime.now());
    }

    @Test
    @Order(1)
    @DisplayName("Conversion PowerStats -> PowerStatsResponseDTO should be correct")
    public void whenConvertPowerStatsEntityToPowerStatsResponseDto_thenCorrect() {

        powerStatsResponseDTO = modelMapper.map(powerStats, PowerStatsResponseDTO.class);

        assertAll("PowerStats and HeroResponseDTO have  to be equals",
                () -> assertEquals(powerStats.getId(), powerStatsResponseDTO.getId()),
                () -> assertEquals(powerStats.getAgility(), powerStatsResponseDTO.getAgility()),
                () -> assertEquals(powerStats.getDexterity(), powerStatsResponseDTO.getDexterity()),
                () -> assertEquals(powerStats.getIntelligence(), powerStatsResponseDTO.getIntelligence()),
                () -> assertEquals(powerStats.getStrength(), powerStatsResponseDTO.getStrength()),
                () -> assertEquals(powerStats.getCreated(), powerStatsResponseDTO.getCreated()),
                () -> assertEquals(powerStats.getUpdated(), powerStatsResponseDTO.getUpdated()));
    }

    @Test
    @Order(2)
    @DisplayName("Conversion Hero -> HeroResponseDTO should be correct")
    public void whenConvertHeroEntityToHeroResponseDto_thenCorrect() {

        powerStats = new PowerStats();
        powerStats.setId(UUID.randomUUID());
        powerStats.setAgility(generateRandomNumber(1, 10));
        powerStats.setDexterity(generateRandomNumber(1, 10));
        powerStats.setIntelligence(generateRandomNumber(1, 10));
        powerStats.setStrength(generateRandomNumber(1, 10));
        powerStats.setCreated(LocalDateTime.now());
        powerStats.setUpdated(LocalDateTime.now());

        System.out.println("powerStats = " + powerStats);

        heroEntity = new Hero();
        heroEntity.setId(UUID.randomUUID());
        heroEntity.setName(generateRandomName(1, 35));
        heroEntity.setRace(pickRandomRace());
        heroEntity.setPowerStats(powerStats);
        heroEntity.setEnabled(true);
        heroEntity.setCreated(LocalDateTime.now());
        heroEntity.setUpdated(LocalDateTime.now());

        System.out.println("heroEntity = " + heroEntity.getPowerStats());

        heroResponseDTO = modelMapper.map(heroEntity, HeroResponseDTO.class);

        System.out.println("heroEntity = " + heroResponseDTO.getPowerStats());

        assertAll("HeroResponseDTO and Hero have  to be equals",
                () -> assertEquals(heroEntity.getId(), heroResponseDTO.getId()),
                () -> assertEquals(heroEntity.getName(), heroResponseDTO.getName()),
                () -> assertEquals(heroEntity.getRace(), heroResponseDTO.getRace()),
                () -> assertEquals(heroEntity.getEnabled(), heroResponseDTO.getEnabled()),
                () -> assertEquals(heroEntity.getCreated(), heroResponseDTO.getCreated()),
                () -> assertEquals(heroEntity.getUpdated(), heroResponseDTO.getUpdated()));


        assertAll("",
                () -> assertTrue(heroResponseDTO.getPowerStats() instanceof PowerStatsResponseDTO),
                () -> assertEquals(heroEntity.getPowerStats().getAgility(),
                        heroResponseDTO.getPowerStats().getAgility()),
                () -> assertEquals(heroEntity.getPowerStats().getDexterity(),
                        heroResponseDTO.getPowerStats().getDexterity()),
                () -> assertEquals(heroEntity.getPowerStats().getIntelligence(),
                        heroResponseDTO.getPowerStats().getIntelligence()),
                () -> assertEquals(heroEntity.getPowerStats().getStrength(),
                        heroResponseDTO.getPowerStats().getStrength())
        );
    }

    @Test
    @Order(3)
    @DisplayName("Conversion PowerStatsResponseDTO -> PowerStats should be correct")
    public void whenConvertPowerStatsResponseDtoToPowerStatsEntity_thenCorrect() {
        PowerStats powerStatsEntity = modelMapper.map(powerStatsResponseDTO, PowerStats.class);

        assertAll("HeroResponseDTO and PowerStats  have  to be equals",
                () -> assertEquals(powerStatsResponseDTO.getId(), powerStatsEntity.getId()),
                () -> assertEquals(powerStatsResponseDTO.getAgility(), powerStatsEntity.getAgility()),
                () -> assertEquals(powerStatsResponseDTO.getDexterity(), powerStatsEntity.getDexterity()),
                () -> assertEquals(powerStatsResponseDTO.getIntelligence(), powerStatsEntity.getIntelligence()),
                () -> assertEquals(powerStatsResponseDTO.getStrength(), powerStatsEntity.getStrength()),
                () -> assertEquals(powerStatsResponseDTO.getCreated(), powerStatsEntity.getCreated()),
                () -> assertEquals(powerStatsResponseDTO.getUpdated(), powerStatsEntity.getUpdated()));
    }

    @Test
    @Order(4)
    @DisplayName("Conversion HeroResponseDTO -> Hero should be correct")
    public void whenConvertHeroResponseDTOtoToHeroEntity_thenCorrect() {
        Hero hero = modelMapper.map(heroResponseDTO, Hero.class);

        assertAll("HeroResponseDTO and Hero have  to be equals",
                () -> assertEquals(heroResponseDTO.getId(), hero.getId()),
                () -> assertEquals(heroResponseDTO.getName(), hero.getName()),
                () -> assertEquals(heroResponseDTO.getRace(), hero.getRace()),
                () -> assertEquals(heroResponseDTO.getEnabled(), hero.getEnabled()),
                () -> assertEquals(heroResponseDTO.getCreated(), hero.getCreated()),
                () -> assertEquals(heroResponseDTO.getUpdated(), hero.getUpdated()));


        assertAll("",
                () -> assertTrue(hero.getPowerStats() instanceof PowerStats),
                () -> assertEquals(heroResponseDTO.getPowerStats().getAgility(), hero.getPowerStats().getAgility()),
                () -> assertEquals(heroResponseDTO.getPowerStats().getDexterity(), hero.getPowerStats().getDexterity()),
                () -> assertEquals(heroResponseDTO.getPowerStats().getIntelligence(), hero.getPowerStats().getIntelligence()),
                () -> assertEquals(heroResponseDTO.getPowerStats().getStrength(), hero.getPowerStats().getStrength())
        );
    }

    @Test
    @Order(5)
    @DisplayName("Conversion  PowerStatsRequestDTO -> PowerStats should be correct")
    public void whenConvertPowerStatsRequestDTOtoToPowerStatsEntity_thenCorrect() {

        powerStatsRequestDTO = new PowerStatsRequestDTO();
        powerStatsRequestDTO.setAgility(generateRandomNumber(1, 10));
        powerStatsRequestDTO.setDexterity(generateRandomNumber(1, 10));
        powerStatsRequestDTO.setIntelligence(generateRandomNumber(1, 10));
        powerStatsRequestDTO.setStrength(generateRandomNumber(1, 10));

        PowerStats powerStats = modelMapper.map(powerStatsRequestDTO, PowerStats.class);

        assertAll("",
                () -> assertEquals(powerStatsRequestDTO.getAgility(), powerStats.getAgility()),
                () -> assertEquals(powerStatsRequestDTO.getDexterity(), powerStats.getDexterity()),
                () -> assertEquals(powerStatsRequestDTO.getIntelligence(), powerStats.getIntelligence()),
                () -> assertEquals(powerStatsRequestDTO.getStrength(), powerStats.getStrength()));
    }

    @Test
    @Order(6)
    @DisplayName("Conversion HeroRequestDTO ->  Hero should be correct")
    public void whenConvertHeroEntityHeroResponseDTOtoToHeroEntity_thenCorrect() {

        HeroRequestDTO heroRequestDTO = new HeroRequestDTO();
        heroRequestDTO.setName(generateRandomName(1, 10));
        heroRequestDTO.setRace(pickRandomRace());
        heroRequestDTO.setPowerStats(powerStatsRequestDTO);
        heroRequestDTO.setEnabled(true);

        Hero hero = modelMapper.map(heroRequestDTO, Hero.class);

        assertAll("",
                () -> assertEquals(heroRequestDTO.getName(), hero.getName()),
                () -> assertEquals(heroRequestDTO.getRace(), hero.getRace()),
                () -> assertEquals(heroRequestDTO.getEnabled(), hero.getEnabled())
        );


        assertAll("",
                () -> assertTrue(hero.getPowerStats() instanceof PowerStats),
                () -> assertEquals(heroRequestDTO.getPowerStats().getAgility(), hero.getPowerStats().getAgility()),
                () -> assertEquals(heroRequestDTO.getPowerStats().getDexterity(), hero.getPowerStats().getDexterity()),
                () -> assertEquals(heroRequestDTO.getPowerStats().getIntelligence(), hero.getPowerStats().getIntelligence()),
                () -> assertEquals(heroRequestDTO.getPowerStats().getStrength(), hero.getPowerStats().getStrength())
        );

    }

    private static Race pickRandomRace() {
        List<Race> races = EnumSet.allOf(Race.class)
                .stream()
                .collect(Collectors.toList());
        Collections.shuffle(races);
        return races.stream()
                .findFirst()
                .get();
    }


    private static int generateRandomNumber(int randomNumberOrigin, int randomNumberBound) {
        return new Random()
                .ints(1, randomNumberOrigin, randomNumberBound)
                .findFirst()
                .getAsInt();
    }

    private String generateRandomName(int randomNumberOrigin, int randomNumberBound) {
        return IntStream.generate(() -> randomNumberOrigin + new Random().nextInt(randomNumberBound))
                .limit(225)
                .mapToObj(i -> Character.toString((char) i))
                .collect(Collectors.joining());
    }
}
