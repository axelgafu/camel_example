# Simple Example (Java DSL)
here's a simple example of Apache Camel using Java DSL:

```java

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class SimpleCamelExample {
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("file:/input")
                .to("file:/output");
            }
        });
        context.start();
        Thread.sleep(5000);
        context.stop();
    }
}
```

In this example, we create a new CamelContext object and add a new RouteBuilder to it. The `configure()` method of the RouteBuilder defines a route that takes a file from the `/input` directory and moves it to the `/output` directory. The `from()` method specifies the input location, while the `to()` method specifies the output location.

Finally, we start the CamelContext and sleep for 5 seconds to allow the route to run. After 5 seconds, we stop the CamelContext.

This is just a simple example, and Apache Camel supports many other features and components, such as integration with messaging systems, web services, and databases.

## Output
``` cmd
[                          main] AbstractCamelContext           INFO  Apache Camel 4.0.0-M1 (camel-1) is starting
[                          main] AbstractCamelContext           INFO  Routes startup (started:1)
[                          main] AbstractCamelContext           INFO      Started route1 (file://C:/Users/axe1_/Downloads/test/input)
[                          main] AbstractCamelContext           INFO  Apache Camel 4.0.0-M1 (camel-1) started in 159ms (build:31ms init:107ms start:21ms)
[                          main] AbstractCamelContext           INFO  Apache Camel 4.0.0-M1 (camel-1) is shutting down (timeout:45s)
[                          main] AbstractCamelContext           INFO  Routes stopped (stopped:1)
[                          main] AbstractCamelContext           INFO      Stopped route1 (file://C:/Users/axe1_/Downloads/test/input)
[                          main] AbstractCamelContext           INFO  Apache Camel 4.0.0-M1 (camel-1) shutdown in 7ms (uptime:5s)

```


---
[Home](https://github.com/axelgafu/camel_example)