package selfstudy.camel.jdsl;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class SimpleJavaDSL  extends RouteBuilder 
{
	public static void main(String[] args) throws Exception 
	{
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new SimpleJavaDSL());
        
        context.start();
        Thread.sleep(5000);
        context.stop();
    }
	
    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() 
    {
    	from("file:" + PATH + "input")
        .to("file:" + PATH + "output");
    }
    
    public static final String PATH = "C:/Users/axe1_/Downloads/test/";

}