package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.enums.Race;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class TestUtils {

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
}
