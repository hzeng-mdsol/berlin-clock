package berlinclock;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HourLampsStrategy implements EncodeStrategy {

    private final int unit;

    public HourLampsStrategy(int unit) {
        this.unit = unit;
    }

    @Override
    public List<Lamp> encode(LocalTime time) {
        int hours = time.getHour();
        int end = (unit == 5) ? hours / 5 : hours % 5;

        Stream<Lamp> redLamps = IntStream.rangeClosed(1, end).boxed().map(lampIndex -> Lamp.RED);
        Stream<Lamp> offLamps = IntStream.range(end, 4).boxed().map(lampIndex -> Lamp.OFF);
        return Stream.concat(redLamps, offLamps).collect(Collectors.toList());
    }
}
