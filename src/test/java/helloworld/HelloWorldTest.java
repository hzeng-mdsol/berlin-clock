package helloworld;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HelloWorldTest {

    @Test
    public void testHelloWorld() {
        HelloWorld helloWorld = new HelloWorld();

        String sayHello = helloWorld.sayHello();

        assertThat(sayHello, equalTo("Hello World"));
    }
}
