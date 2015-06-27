package helloworld;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HelloWorldTest {

    @Test
    public void testHelloWorld() {
        // Given
        HelloWorld helloWorld = new HelloWorld();

        // When
        String sayHello = helloWorld.sayHello();

        // Then
        assertThat(sayHello, equalTo("Hello World"));
    }
}
