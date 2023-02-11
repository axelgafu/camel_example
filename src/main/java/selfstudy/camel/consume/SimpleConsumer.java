package selfstudy.camel.consume;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class SimpleConsumer extends RouteBuilder 
{
	public static void main(String[] args) throws Exception
	{
		CamelContext context = new DefaultCamelContext();
		
		context.addRoutes( new SimpleConsumer() );
		context.start(); // IMPORTANT: Start context first.
		
		
		/*
		 * Create a sample message in "direct:start" topic.
		 */
		ProducerTemplate producer = context.createProducerTemplate();
		producer.sendBody("direct:start", "Hello everyone");
		
		
		/*
		 * Consume the sample message through the "seda:end" topic.
		 * See configure method, it shows how the message is 
		 * transferred from "direct:start" to "seda:end".
		 */
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
	
}
