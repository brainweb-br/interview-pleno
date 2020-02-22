package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.enums.Race;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
}
