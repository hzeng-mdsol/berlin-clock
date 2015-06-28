package rest;

import berlinclock.BerlinClock;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/berlin-clock")
@Produces(MediaType.TEXT_PLAIN)
public class BerlinClockResource {

    @GET
    public String getBerlinTime(@QueryParam("time") String time) {
        return new BerlinClock().getTime(time);
    }
}