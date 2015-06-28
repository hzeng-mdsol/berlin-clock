package berlinclock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BerlinClock {

    private final BlinkingLampStrategy blinkingLampStrategy;
    private final HourLampsStrategy fiveHourLampsStrategy;
    private final HourLampsStrategy oneHourLampsStrategy;

    public BerlinClock() {
        this.blinkingLampStrategy = new BlinkingLampStrategy();
        this.fiveHourLampsStrategy = new HourLampsStrategy(5);
        this.oneHourLampsStrategy = new HourLampsStrategy(1);
    }

    public String getTime(String time) {
        LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss"));

        return printLamps(blinkingLampStrategy.calculate(localTime)) +
                printLamps(fiveHourLampsStrategy.calculate(localTime)) +
                printLamps(oneHourLampsStrategy.calculate(localTime)) +
                printLamps(Arrays.asList(Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF)) +
                printLamps(Arrays.asList(Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF));
    }

    private String printLamps(List<Lamp> lamps) {
        return lamps.stream().map(Lamp::getLamp).collect(Collectors.joining());
    }
}
