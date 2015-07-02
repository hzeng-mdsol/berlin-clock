package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import berlinclock.BerlinClock;


@Path("/useberlinclock")
@Produces(MediaType.TEXT_PLAIN)
public class UseBerlinClock {
	private BerlinClock myClock = new BerlinClock(); 
	
	@GET
	public String printTime(@QueryParam("time") String time){
		return myClock.getTime(time);
	}

}
