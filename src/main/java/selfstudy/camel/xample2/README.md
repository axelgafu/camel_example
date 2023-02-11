# Routes and Endpoints
Here's an example of an Apache Camel route that has two sub-routes, a transformation step, and two endpoints:

``` java
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

public class TwoRouteExample extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:start")
                .routeId("FirstRoute")
                .setHeader("Operation", constant("Transform"))
                .setBody(constant("Original Message"))
                .to("direct:transform");

        from("direct:transform")
                .routeId("SecondRoute")
                .setHeader("Operation", constant("Process"))
                .transform(body().prepend("Transformed: "))
                .to("mock:result");

    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.addRouteBuilder(new TwoRouteExample());
        main.run();
    }
}
```

In this example, we define two sub-routes that are connected by a direct endpoint. The first route, `"FirstRoute"`, starts by listening to the `"direct:start"` endpoint. It then sets a header and the body of the message, and sends the message to the `"direct:transform"` endpoint.

The second route, `"SecondRoute"`, listens to the `"direct:transform"` endpoint. It sets a header and transforms the message by adding a prefix to the body. It then sends the message to the `"mock:result"` endpoint, which is a built-in endpoint in Apache Camel that allows us to easily test our routes.

To run this example, you can use the following code to send a message to the `"direct:start"` endpoint:

``` java
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

public class MainApp {

    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new TwoRouteExample());
        context.start();

        ProducerTemplate producerTemplate = context.createProducerTemplate();
        producerTemplate.sendBody("direct:start", "Hello, World!");

        context.stop();
    }
}
```

This will trigger the `"FirstRoute"` to send the message to the `"SecondRoute"`, where it will be transformed and sent to the `"mock:result"` endpoint.


# Beans
Here's an example of an Apache Camel route that receives a message from a direct endpoint, passes it to a bean to modify the input by appending "Modified", and then displays the result in the console:

``` java
from("direct:start")
    .bean(MyBean.class, "modify")
    .to("log:modified?showBody=true");

public class MyBean {
    public String modify(String input) {
        return input + "Modified";
    }
}
```

In this example, the route starts with a direct endpoint called `"start"`. The bean component is used to invoke the modify method of the MyBean class, passing in the message body as a parameter. The modified message is then logged to the console using the log component with a log name of `"modified"` and the showBody option set to true to display the message body.

Note that the MyBean class must have a public method named `"modify"` that takes a single parameter of type String and returns a String. This method is invoked by the bean component and is responsible for modifying the input message as required.


---
[Home](https://github.com/axelgafu/camel_example)