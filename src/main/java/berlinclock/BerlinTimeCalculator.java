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

    public Stream<Lamp> calculateBlinkingLamp() {
        return Stream.of(time.getSecond() % 2 == 0 ? Lamp.YELLOW : Lamp.OFF);
    }

    public Stream<Lamp> calculateFiveHourLamps() {
        return calculateLampRow(time.getHour() / 5, 4, index -> Lamp.RED, index -> Lamp.OFF);
    }

    public Stream<Lamp> calculateOneHourLamps() {
        return calculateLampRow(time.getHour() % 5, 4, index -> Lamp.RED, index -> Lamp.OFF);
    }

    public Stream<Lamp> calculateOneMinuteLamps() {
        return calculateLampRow(time.getMinute() % 5, 4, index -> Lamp.YELLOW, index -> Lamp.OFF);
    }

    public Stream<Lamp> calculateFiveMinuteLamps() {
        return calculateLampRow(time.getMinute() / 5, 11, index -> index % 3 == 0 ? Lamp.RED : Lamp.YELLOW, index -> Lamp.OFF);
    }

    private Stream<Lamp> calculateLampRow(int onOffCount, int lamps, Function<Integer, Lamp> on, Function<Integer, Lamp> off) {
        return Stream.concat(IntStream.rangeClosed(1, onOffCount).boxed().map(on),
                                IntStream.range(onOffCount, lamps).boxed().map(off));
    }
}
