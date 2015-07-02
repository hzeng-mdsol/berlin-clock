package rest;

import org.junit.Rule;
import org.junit.Test;

import io.dropwizard.testing.junit.ResourceTestRule;
import rest.HelloWorld;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class UseBerlinClockTest {
	
	@Rule
	public final ResourceTestRule resources = ResourceTestRule.builder().addResource(new UseBerlinClock()).build();
	
	@Test
	public void testUseBerlinClock() {
		String greeting = resources.client().target("/useberlinclock")
				.queryParam("time", "12:00:00")
				.request().get(String.class);
		assertThat(greeting, equalTo("Y\nRROO\nRROO\nOOOOOOOOOOO\nOOOO"));
		}

}