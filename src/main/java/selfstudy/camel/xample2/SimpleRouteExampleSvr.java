package selfstudy.camel.xample2;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class SimpleRouteExampleSvr  extends RouteBuilder
{
	public static void main(String[] args) throws Exception 
	{
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new SimpleRouteExampleSvr());
		
		context.start();
		Thread.sleep(5000);
		context.stop();
	}
	
	

    @Override
    public void configure() throws Exception {
        from("direct:start")
                .routeId("FirstRoute")
                .setHeader("Operation", constant("Transform"))
                //.setBody(constant("Original Message"))
                .to("direct:transform");

        from("direct:transform")
                .routeId("SecondRoute")
                .setHeader("Operation", constant("Process"))
                .transform(body().prepend("Transformed: "))
                .bean(MyBean.class, "modify")
                .to("mock:result")
                .to("log:modified?showBody=true");

    }

}

class MyBean
{
	public static String modify(String input)
	{
		return "Modified:" + input;
	}
}