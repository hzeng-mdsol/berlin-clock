package rest;

import berlinclock.BerlinClock;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BerlinClockResourceTest {
    @Rule
    public final ResourceTestRule resources = ResourceTestRule.builder()
                                                                .addResource(new BerlinClockResource())
                                                                .build();

    @Test
    public void testGetBerlinTime() {
        String time = "18:03:01";
        String berlinTime = "O\nRRRO\nRRRO\nOOOOOOOOOOO\nYYYO";

        BerlinClock berlinClock = mock(BerlinClock.class);
        when(berlinClock.getTime(time)).thenReturn(berlinTime);

        assertThat(resources.client().target("/berlin-clock")
                                     .queryParam("time", time)
                                     .request()
                                     .get(String.class))
                .isEqualTo(berlinTime);
    }
}
