package berlinclock;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HourLampsStrategy implements CalculateTimeStrategy {

    private final int unit;

    public HourLampsStrategy(int unit) {
        if (unit != 5 && unit != 1) {
            throw new IllegalArgumentException(String.format("Unexpected unit '%s' for hour lamps.", unit));
        }
        this.unit = unit;
    }

    @Override
    public List<Lamp> calculate(LocalTime time) {
        int hours = time.getHour();
        int onOffIndex = (unit == 5) ? hours / 5 : hours % 5;

        Stream<Lamp> redLamps = IntStream.rangeClosed(1, onOffIndex).boxed().map(lampIndex -> Lamp.RED);
        Stream<Lamp> offLamps = IntStream.range(onOffIndex, 4).boxed().map(lampIndex -> Lamp.OFF);
        return Stream.concat(redLamps, offLamps).collect(Collectors.toList());
    }
}
