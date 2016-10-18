package hu.szakdolgozat.carsharing;

import java.util.Random;

public class TestUtils {

    private static Random random = new Random();

    public static double getRandomDoubleInRange(double min, double max) {
        double range = max - min;
        double scaled = random.nextDouble() * range;
        return scaled + min;
    }
}

