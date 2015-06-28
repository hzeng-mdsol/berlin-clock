package berlinclock;

import org.junit.Test;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BerlinTimeCalculatorTest {

    @Test
    public void shouldCalculateAllFiveHourLampsAsOff() {
        Stream<Lamp> actualLamps = new BerlinTimeCalculator(LocalTime.of(0, 0, 0)).calculateFiveHourLamps();
        assertLampsMatch(actualLamps, Arrays.asList(Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF));
    }

    @Test
    public void shouldCalculateAllFiveHourLampsAsRed() {
        Stream<Lamp> actualLamps = new BerlinTimeCalculator(LocalTime.of(20, 0, 0)).calculateFiveHourLamps();
        assertLampsMatch(actualLamps, Arrays.asList(Lamp.RED, Lamp.RED, Lamp.RED, Lamp.RED));
    }

    @Test
    public void shouldCalculateSomeFiveHourLampsAsRed() {
        Stream<Lamp> actualLamps = new BerlinTimeCalculator(LocalTime.of(15, 23, 6)).calculateFiveHourLamps();
        assertLampsMatch(actualLamps, Arrays.asList(Lamp.RED, Lamp.RED, Lamp.RED, Lamp.OFF));
    }

    @Test
    public void shouldCalculateAllOneHourLampsAsOff() {
        Stream<Lamp> actualLamps = new BerlinTimeCalculator(LocalTime.of(10, 23, 46)).calculateOneHourLamps();
        assertLampsMatch(actualLamps, Arrays.asList(Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF));
    }

    @Test
    public void shouldCalculateAllOneHourLampsAsRed() {
        Stream<Lamp> actualLamps = new BerlinTimeCalculator(LocalTime.of(14, 13, 47)).calculateOneHourLamps();
        assertLampsMatch(actualLamps, Arrays.asList(Lamp.RED, Lamp.RED, Lamp.RED, Lamp.RED));
    }

    @Test
    public void shouldCalculateAllOneHourLampsAsRedAbove() {
        Stream<Lamp> actualLamps = new BerlinTimeCalculator(LocalTime.of(16, 23, 46)).calculateOneHourLamps();
        assertLampsMatch(actualLamps, Arrays.asList(Lamp.RED, Lamp.OFF, Lamp.OFF, Lamp.OFF));
    }

    @Test
    public void shouldCalculateSomeOneHourLampsAsRedBelow() {
        Stream<Lamp> actualLamps = new BerlinTimeCalculator(LocalTime.of(13, 12, 9)).calculateOneHourLamps();
        assertLampsMatch(actualLamps, Arrays.asList(Lamp.RED, Lamp.RED, Lamp.RED, Lamp.OFF));
    }

    @Test
    public void shouldCalculateAllOneMinuteLampsAsOff() {
        Stream<Lamp> actualLamps = new BerlinTimeCalculator(LocalTime.of(10, 20, 46)).calculateOneMinuteLamps();
        assertLampsMatch(actualLamps, Arrays.asList(Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF));
    }

    @Test
    public void shouldCalculateAllOneMinuteLampsAsYellow() {
        Stream<Lamp> actualLamps = new BerlinTimeCalculator(LocalTime.of(14, 4, 47)).calculateOneMinuteLamps();
        assertLampsMatch(actualLamps, Arrays.asList(Lamp.YELLOW, Lamp.YELLOW, Lamp.YELLOW, Lamp.YELLOW));
    }

    @Test
    public void shouldCalculateAllOneMinuteLampsAsRedAbove() {
        Stream<Lamp> actualLamps = new BerlinTimeCalculator(LocalTime.of(16, 16, 46)).calculateOneMinuteLamps();
        assertLampsMatch(actualLamps, Arrays.asList(Lamp.YELLOW, Lamp.OFF, Lamp.OFF, Lamp.OFF));
    }

    @Test
    public void shouldCalculateSomeOneMinuteLampsAsRedBelow() {
        Stream<Lamp> actualLamps = new BerlinTimeCalculator(LocalTime.of(7, 13, 9)).calculateOneMinuteLamps();
        assertLampsMatch(actualLamps, Arrays.asList(Lamp.YELLOW, Lamp.YELLOW, Lamp.YELLOW, Lamp.OFF));
    }

    @Test
    public void shouldCalculateFiveMinuteLampsForHalfPast() {
        Stream<Lamp> actualLamps = new BerlinTimeCalculator(LocalTime.of(7, 30, 0)).calculateFiveMinuteLamps();
        assertLampsMatch(actualLamps, Arrays.asList(Lamp.YELLOW, Lamp.YELLOW, Lamp.RED, Lamp.YELLOW, Lamp.YELLOW, Lamp.RED, Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF));
    }

    @Test
    public void shouldCalculateAllFiveMinuteLampsAsOn() {
        Stream<Lamp> actualLamps = new BerlinTimeCalculator(LocalTime.of(23, 59, 0)).calculateFiveMinuteLamps();
        assertLampsMatch(actualLamps, Arrays.asList(Lamp.YELLOW, Lamp.YELLOW, Lamp.RED, Lamp.YELLOW, Lamp.YELLOW, Lamp.RED, Lamp.YELLOW, Lamp.YELLOW, Lamp.RED, Lamp.YELLOW, Lamp.YELLOW));
    }

    private void assertLampsMatch(Stream<Lamp> actualLamps, List<Lamp> expectedLamps) {
        assertThat(actualLamps.collect(Collectors.toList()), equalTo(expectedLamps));
    }
}
