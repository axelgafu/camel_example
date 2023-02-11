package selfstudy.camel.xample2;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

public class SimpleRouteExampleCnt 
{
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new SimpleRouteExampleSvr());
        context.start();

        ProducerTemplate producerTemplate = context.createProducerTemplate();
        producerTemplate.sendBody("direct:start", "Hello, World!");

        Thread.sleep(5000);
        context.stop();
    }
}
