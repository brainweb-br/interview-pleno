package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.dtos.request.HeroRequestDTO;
import br.com.brainweb.interview.model.dtos.request.PowerStatsRequestDTO;
import br.com.brainweb.interview.model.dtos.response.HeroResponseDTO;
import br.com.brainweb.interview.model.dtos.response.PowerStatsResponseDTO;
import br.com.brainweb.interview.model.entities.Hero;
import br.com.brainweb.interview.model.entities.PowerStats;
import br.com.brainweb.interview.model.enums.Race;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class TestUtils {

    private static final LocalDateTime CREATED_AT = generateRandomDateTime();
    private static final LocalDateTime UPDATED_AT = generateRandomDateTime();

    public static Race pickRandomRace() {
        List<Race> races = EnumSet.allOf(Race.class)
                .stream()
                .collect(Collectors.toList());
        Collections.shuffle(races);
        return races.stream()
                .findFirst()
                .get();
    }


    public static int generateRandomNumber(int randomNumberOrigin, int randomNumberBound) {
        return new Random()
                .ints(1, randomNumberOrigin, randomNumberBound)
                .findFirst()
                .getAsInt();
    }

    public static String generateRandomName(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static boolean generateRandomBoolean() {
        return System.currentTimeMillis() % 2 == 0 ? true : false;
    }

    public static LocalDateTime generateRandomDateTime() {

        long startEpochDay = LocalDate.of(2020, Month.FEBRUARY, 17).toEpochDay();
        long endEpochDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        int startSeconds = LocalTime.MIDNIGHT.toSecondOfDay();
        int endSeconds = LocalTime.of(8, 30).toSecondOfDay();
        int randomTime = ThreadLocalRandom
                .current()
                .nextInt(startSeconds, endSeconds);

        return LocalDateTime.of(LocalDate.ofEpochDay(randomDay), LocalTime.ofSecondOfDay(randomTime));
    }

    public static PowerStatsResponseDTO createPowerStatsResponseDTO() {

        return new PowerStatsResponseDTO(UUID.randomUUID(),
                generateRandomNumber(1, 10),
                generateRandomNumber(1, 10),
                generateRandomNumber(1, 10),
                generateRandomNumber(1, 10),
                CREATED_AT, UPDATED_AT);

    }

    public static HeroResponseDTO createHeroResponseDTO() {

        return new HeroResponseDTO(UUID.randomUUID(),
                generateRandomName(10),
                pickRandomRace(),
                createPowerStatsResponseDTO(),
                generateRandomBoolean(),
                CREATED_AT, UPDATED_AT);
    }

    public static PowerStatsRequestDTO createPowerStatsRequestDTO() {

        return new PowerStatsRequestDTO(generateRandomNumber(1, 10),
                generateRandomNumber(1, 10),
                generateRandomNumber(1, 10),
                generateRandomNumber(1, 10));
    }

    public static HeroRequestDTO createHeroRequestDTO() {

        return new HeroRequestDTO(generateRandomName(10),
                pickRandomRace(),
                createPowerStatsRequestDTO(),
                generateRandomBoolean());
    }

    public static PowerStats createPowerStats() {
        return new PowerStats(generateRandomNumber(1, 10),
                generateRandomNumber(1, 10),
                generateRandomNumber(1, 10),
                generateRandomNumber(1, 10));
    }

    public static Hero createHero() {
        return new Hero(generateRandomName(10),
                pickRandomRace(),
                createPowerStats(),
                generateRandomBoolean());
    }
}
