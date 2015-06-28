package berlinclock;

import java.time.LocalTime;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BerlinTimeCalculator {
    private final LocalTime time;

    public BerlinTimeCalculator(LocalTime time) {
        this.time = time;
    }

    public Stream<BerlinLamp> calculateBlinkingLamp() {
        return Stream.of(time.getSecond() % 2 == 0 ? BerlinLamp.YELLOW : BerlinLamp.OFF);
    }

    public Stream<BerlinLamp> calculateFiveHourLamps() {
        return calculateBerlinLampRow(time.getHour() / 5, 4, index -> BerlinLamp.RED, index -> BerlinLamp.OFF);
    }

    public Stream<BerlinLamp> calculateOneHourLamps() {
        return calculateBerlinLampRow(time.getHour() % 5, 4, index -> BerlinLamp.RED, index -> BerlinLamp.OFF);
    }

    public Stream<BerlinLamp> calculateOneMinuteLamps() {
        return calculateBerlinLampRow(time.getMinute() % 5, 4, index -> BerlinLamp.YELLOW, index -> BerlinLamp.OFF);
    }

    public Stream<BerlinLamp> calculateFiveMinuteLamps() {
        return calculateBerlinLampRow(time.getMinute() / 5, 11, index -> index % 3 == 0 ? BerlinLamp.RED : BerlinLamp.YELLOW, index -> BerlinLamp.OFF);
    }

    private Stream<BerlinLamp> calculateBerlinLampRow(int onOffCount, int lamps, Function<Integer, BerlinLamp> on, Function<Integer, BerlinLamp> off) {
        return Stream.concat(IntStream.rangeClosed(1, onOffCount).boxed().map(on),
                                IntStream.range(onOffCount, lamps).boxed().map(off));
    }
}
