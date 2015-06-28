package berlinclock;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MinuteLampsStrategy implements CalculateTimeStrategy {
    private final int unit;

    public MinuteLampsStrategy(int unit) {
        this.unit = unit;
    }

    @Override
    public List<Lamp> calculate(LocalTime time) {
        int minutes = time.getMinute();

        Stream<Lamp> yellowLamps = IntStream.rangeClosed(1, minutes % 5).boxed().map(lampIndex -> Lamp.YELLOW);
        Stream<Lamp> offLamps = IntStream.range(minutes % 5, 4).boxed().map(lampIndex -> Lamp.OFF);
        return Stream.concat(yellowLamps, offLamps).collect(Collectors.toList());
    }
}
