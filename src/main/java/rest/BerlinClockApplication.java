package rest;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BerlinClockApplication extends Application<BerlinClockConfiguration> {
    public static void main(String[] args) throws Exception {
        new BerlinClockApplication().run(args);
    }

    @Override
    public String getName() {
        return "berlin-clock";
    }

    @Override
    public void initialize(Bootstrap<BerlinClockConfiguration> bootstrap) {
    }

    @Override
    public void run(BerlinClockConfiguration berlinClockConfiguration, Environment environment) throws Exception {
        //TODO
    }
}
