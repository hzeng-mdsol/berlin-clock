package rest;

import berlinclock.BerlinClock;
import com.google.common.base.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.time.LocalTime;

@Path("/berlin-clock")
@Produces(MediaType.TEXT_PLAIN)
public class BerlinClockResource {

    @GET
    public String getBerlinTime(@QueryParam("time") Optional<String> time) {
        return new BerlinClock().getTime(time.or(LocalTime.now().toString()));
    }
}