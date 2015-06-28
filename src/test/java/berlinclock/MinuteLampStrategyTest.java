package berlinclock;

import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalTime;
import java.util.Arrays;

public class MinuteLampStrategyTest {
    private static MinuteLampsStrategy oneMinuteLampsStrategy;

    @BeforeClass
    public static void setupStrategies() {
        oneMinuteLampsStrategy = new MinuteLampsStrategy(1);
    }

    @Test
    public void shouldCalculateAllOneHourLampsAsOff() {
        CalculateTimeChecker.check(LocalTime.of(10, 20, 46), oneMinuteLampsStrategy, Arrays.asList(Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF));
    }

    @Test
    public void shouldCalculateAllOneMinuteLampsAsYellow() {
        CalculateTimeChecker.check(LocalTime.of(14, 4, 47), oneMinuteLampsStrategy, Arrays.asList(Lamp.YELLOW, Lamp.YELLOW, Lamp.YELLOW, Lamp.YELLOW));
    }

    @Test
    public void shouldCalculateAllOneHourLampsAsRedAbove() {
        CalculateTimeChecker.check(LocalTime.of(16, 16, 46), oneMinuteLampsStrategy, Arrays.asList(Lamp.YELLOW, Lamp.OFF, Lamp.OFF, Lamp.OFF));
    }

    @Test
    public void shouldCalculateSomeOneHourLampsAsRedBelow() {
        CalculateTimeChecker.check(LocalTime.of(7, 13, 9), oneMinuteLampsStrategy, Arrays.asList(Lamp.YELLOW, Lamp.YELLOW, Lamp.YELLOW, Lamp.OFF));
    }
}
