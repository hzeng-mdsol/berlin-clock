package berlinclock;

import java.time.LocalTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BerlinClock {
    public String getTime(String time) {
        BerlinTimeCalculator calculator = new BerlinTimeCalculator(LocalTime.parse(time));

        return Stream.of(calculator.calculateBlinkingLamp(),
                            calculator.calculateFiveHourLamps(),
                            calculator.calculateOneHourLamps(),
                            calculator.calculateFiveMinuteLamps(),
                            calculator.calculateOneMinuteLamps())
                    .map(lamps -> lamps.map(BerlinLamp::getLamp).collect(Collectors.joining()))
                    .collect(Collectors.joining("\n"));
    }
}
