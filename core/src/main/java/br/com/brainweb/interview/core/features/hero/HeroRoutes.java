package br.com.brainweb.interview.core.features.hero;

import javax.servlet.Servlet;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.brainweb.interview.model.Hero;


@Component
public class HeroRoutes extends RouteBuilder{

    @Override
    public void configure() throws Exception {
	
	restConfiguration()
		.component("servlet")
		.bindingMode(RestBindingMode.json)
		.jsonDataFormat("json-gson")
		.dataFormatProperty("prettyPrint", "true")
		.apiContextPath("api-doc")
		.contextPath("/ws")
		.apiVendorExtension(true)
    	        	.apiProperty("api.title", "Hero API").apiProperty("api.version", "1.0.0")
    	        	.apiProperty("cors", "true");
	
	
	rest("/v1/heroes")
	
        	.get("/{id}")
                	.responseMessage().code(404).message("Hero not found").endResponseMessage()
                	.route()
                	.to("bean:heroController?method=findById(${header.id})")
                	.onException(HeroNotFoundException.class).process(exchange -> { 
                	    	exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, 404);
                	    	exchange.getMessage().setBody(new Error("Not found"), Error.class);
                	    })
                	.handled(true)
                	.endRest()
        	
        	
        	.get("/name/{name}")
        		.to("bean:heroController?method=findByName(${header.name})")
        		.route()
        		.process(exchange -> {
        		    exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 200);
        		}).endRest()
        	
        	.post()
        		.type(Hero.class)
        		.to("bean:heroController?method=create(${body})")
        		
        	.put()
        		.type(Hero.class)
        		.responseMessage().code(404).message("Hero not found").endResponseMessage()
        		.route()
        		.to("bean:heroController?method=update(${body})")
        		.onException(HeroNotFoundException.class).process(exchange -> { 
                	    	exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, 404);
                	    	exchange.getMessage().setBody(new Error("Not found"), Error.class);
                	    })
                	.handled(true)
                	.endRest()
                	
                .delete("/id/{id}")
        		.responseMessage().code(404).message("Hero not found").endResponseMessage()
        		.route()
        		.to("bean:heroController?method=delete(${header.id})")
        		.onException(HeroNotFoundException.class).process(exchange -> { 
                	    	exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, 404);
                	    	exchange.getMessage().setBody(new Error("Not found"), Error.class);
                	    })
                	.handled(true)
                	.endRest()
        	
        	;
	
    }
    
    @Bean
    public ServletRegistrationBean<Servlet> camelServletBean(){
	System.out.println("Criando CamelServlet...");
	ServletRegistrationBean<Servlet> servletRegistrationBean = new ServletRegistrationBean<Servlet>(new CamelHttpTransportServlet(), "/ws/*");
	servletRegistrationBean.setName("CamelServlet");
	return servletRegistrationBean;
    }

}
