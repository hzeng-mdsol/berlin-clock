package rest;

import org.junit.Rule;
import org.junit.Test;

import io.dropwizard.testing.junit.ResourceTestRule;
import rest.HelloWorld;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class HelloWorldTest {
	
	@Rule
	public final ResourceTestRule resources = ResourceTestRule.builder().addResource(new HelloWorld()).build();
	
	@Test
	public void testHelloWorld() {
		String greeting = resources.client().target("/helloworld").request().get(String.class);
		assertThat(greeting, equalTo("Hello World"));
	}


}
