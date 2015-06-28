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
        Stream<BerlinLamp> actualBerlinLamps = new BerlinTimeCalculator(LocalTime.of(0, 0, 0)).calculateFiveHourLamps();
        assertBerlinLampsMatch(actualBerlinLamps, Arrays.asList(BerlinLamp.OFF, BerlinLamp.OFF, BerlinLamp.OFF, BerlinLamp.OFF));
    }

    @Test
    public void shouldCalculateAllFiveHourLampsAsRed() {
        Stream<BerlinLamp> actualBerlinLamps = new BerlinTimeCalculator(LocalTime.of(20, 0, 0)).calculateFiveHourLamps();
        assertBerlinLampsMatch(actualBerlinLamps, Arrays.asList(BerlinLamp.RED, BerlinLamp.RED, BerlinLamp.RED, BerlinLamp.RED));
    }

    @Test
    public void shouldCalculateSomeFiveHourLampsAsRed() {
        Stream<BerlinLamp> actualBerlinLamps = new BerlinTimeCalculator(LocalTime.of(15, 23, 6)).calculateFiveHourLamps();
        assertBerlinLampsMatch(actualBerlinLamps, Arrays.asList(BerlinLamp.RED, BerlinLamp.RED, BerlinLamp.RED, BerlinLamp.OFF));
    }

    @Test
    public void shouldCalculateAllOneHourLampsAsOff() {
        Stream<BerlinLamp> actualBerlinLamps = new BerlinTimeCalculator(LocalTime.of(10, 23, 46)).calculateOneHourLamps();
        assertBerlinLampsMatch(actualBerlinLamps, Arrays.asList(BerlinLamp.OFF, BerlinLamp.OFF, BerlinLamp.OFF, BerlinLamp.OFF));
    }

    @Test
    public void shouldCalculateAllOneHourLampsAsRed() {
        Stream<BerlinLamp> actualBerlinLamps = new BerlinTimeCalculator(LocalTime.of(14, 13, 47)).calculateOneHourLamps();
        assertBerlinLampsMatch(actualBerlinLamps, Arrays.asList(BerlinLamp.RED, BerlinLamp.RED, BerlinLamp.RED, BerlinLamp.RED));
    }

    @Test
    public void shouldCalculateAllOneHourLampsAsRedAbove() {
        Stream<BerlinLamp> actualBerlinLamps = new BerlinTimeCalculator(LocalTime.of(16, 23, 46)).calculateOneHourLamps();
        assertBerlinLampsMatch(actualBerlinLamps, Arrays.asList(BerlinLamp.RED, BerlinLamp.OFF, BerlinLamp.OFF, BerlinLamp.OFF));
    }

    @Test
    public void shouldCalculateSomeOneHourLampsAsRedBelow() {
        Stream<BerlinLamp> actualBerlinLamps = new BerlinTimeCalculator(LocalTime.of(13, 12, 9)).calculateOneHourLamps();
        assertBerlinLampsMatch(actualBerlinLamps, Arrays.asList(BerlinLamp.RED, BerlinLamp.RED, BerlinLamp.RED, BerlinLamp.OFF));
    }

    @Test
    public void shouldCalculateAllOneMinuteLampsAsOff() {
        Stream<BerlinLamp> actualBerlinLamps = new BerlinTimeCalculator(LocalTime.of(10, 20, 46)).calculateOneMinuteLamps();
        assertBerlinLampsMatch(actualBerlinLamps, Arrays.asList(BerlinLamp.OFF, BerlinLamp.OFF, BerlinLamp.OFF, BerlinLamp.OFF));
    }

    @Test
    public void shouldCalculateAllOneMinuteLampsAsYellow() {
        Stream<BerlinLamp> actualBerlinLamps = new BerlinTimeCalculator(LocalTime.of(14, 4, 47)).calculateOneMinuteLamps();
        assertBerlinLampsMatch(actualBerlinLamps, Arrays.asList(BerlinLamp.YELLOW, BerlinLamp.YELLOW, BerlinLamp.YELLOW, BerlinLamp.YELLOW));
    }

    @Test
    public void shouldCalculateAllOneMinuteLampsAsRedAbove() {
        Stream<BerlinLamp> actualBerlinLamps = new BerlinTimeCalculator(LocalTime.of(16, 16, 46)).calculateOneMinuteLamps();
        assertBerlinLampsMatch(actualBerlinLamps, Arrays.asList(BerlinLamp.YELLOW, BerlinLamp.OFF, BerlinLamp.OFF, BerlinLamp.OFF));
    }

    @Test
    public void shouldCalculateSomeOneMinuteLampsAsRedBelow() {
        Stream<BerlinLamp> actualBerlinLamps = new BerlinTimeCalculator(LocalTime.of(7, 13, 9)).calculateOneMinuteLamps();
        assertBerlinLampsMatch(actualBerlinLamps, Arrays.asList(BerlinLamp.YELLOW, BerlinLamp.YELLOW, BerlinLamp.YELLOW, BerlinLamp.OFF));
    }

    @Test
    public void shouldCalculateFiveMinuteLampsForHalfPast() {
        Stream<BerlinLamp> actualBerlinLamps = new BerlinTimeCalculator(LocalTime.of(7, 30, 0)).calculateFiveMinuteLamps();
        assertBerlinLampsMatch(actualBerlinLamps, Arrays.asList(BerlinLamp.YELLOW, BerlinLamp.YELLOW, BerlinLamp.RED, BerlinLamp.YELLOW, BerlinLamp.YELLOW, BerlinLamp.RED, BerlinLamp.OFF, BerlinLamp.OFF, BerlinLamp.OFF, BerlinLamp.OFF, BerlinLamp.OFF));
    }

    @Test
    public void shouldCalculateAllFiveMinuteLampsAsOn() {
        Stream<BerlinLamp> actualBerlinLamps = new BerlinTimeCalculator(LocalTime.of(23, 59, 0)).calculateFiveMinuteLamps();
        assertBerlinLampsMatch(actualBerlinLamps, Arrays.asList(BerlinLamp.YELLOW, BerlinLamp.YELLOW, BerlinLamp.RED, BerlinLamp.YELLOW, BerlinLamp.YELLOW, BerlinLamp.RED, BerlinLamp.YELLOW, BerlinLamp.YELLOW, BerlinLamp.RED, BerlinLamp.YELLOW, BerlinLamp.YELLOW));
    }

    private void assertBerlinLampsMatch(Stream<BerlinLamp> actualBerlinLamps, List<BerlinLamp> expectedBerlinLamps) {
        assertThat(actualBerlinLamps.collect(Collectors.toList()), equalTo(expectedBerlinLamps));
    }
}
