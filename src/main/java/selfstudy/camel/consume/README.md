# Simple Consumer
This example will pass an input to a consumer object using Camel:


Create a sample message in "direct:start" topic.

``` java
ProducerTemplate producer = context.createProducerTemplate();
producer.sendBody("direct:start", "Hello everyone");
```

Consume the sample message through the "seda:end" topic; See configure method, it shows how the message is 
transferred from "direct:start" to "seda:end". Second parameter to `receiveBody` is a String class because
that is the data type passed in the second parameter in the `sendBody` example above.

``` java
ConsumerTemplate consumer = context.createConsumerTemplate();
String message = consumer.receiveBody("seda:end", String.class);
		
System.out.println(message);
```


## Complete example.

``` java
	public static void main(String[] args) throws Exception
	{
		CamelContext context = new DefaultCamelContext();
		
		context.addRoutes( new SimpleConsumer() );
		context.start(); // IMPORTANT: Start context first.
		
		ProducerTemplate producer = context.createProducerTemplate();
		producer.sendBody("direct:start", "Hello everyone");

		ConsumerTemplate consumer = context.createConsumerTemplate();
		String message = consumer.receiveBody("seda:end", String.class);
		
		System.out.println(message);
	}

	/**
	 * Implements Apache Camel {@link RouteBuilder#configure()}
	 * 
	 * Transfer "direct:start" -> "seda:end".
	 */
	@Override
	public void configure() throws Exception 
	{
		from("direct:start")
		.to("seda:end");
	}
```

## Output
``` cmd
[                          main] AbstractCamelContext           INFO  Apache Camel 4.0.0-M1 (camel-1) is starting
[                          main] AbstractCamelContext           INFO  Routes startup (started:1)
[                          main] AbstractCamelContext           INFO      Started route1 (direct://start)
[                          main] AbstractCamelContext           INFO  Apache Camel 4.0.0-M1 (camel-1) started in 128ms (build:32ms init:84ms start:12ms)
Hello everyone
```

---
[Home](https://github.com/axelgafu/camel_example)