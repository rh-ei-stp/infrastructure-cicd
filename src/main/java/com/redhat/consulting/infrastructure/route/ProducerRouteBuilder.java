package com.redhat.consulting.infrastructure.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProducerRouteBuilder extends RouteBuilder {

	@Value("${producer.queue.name}")
	private String queueName;
	
	@Value("${producer.route.switch}")
	private boolean runRoute;
	
	@Override
	public void configure() throws Exception {
		
		// @formatter:off

		onException(Exception.class)
				.handled(true)
				.transform(simple("${exception.message}"))
				.log("Exception: ${body}");

		from("timer://foo?fixedRate=true&period=1000")
			.routeId("producer")
			.autoStartup(runRoute)
			.setBody(simple("Test Message at ->" + "${date:now}"))
			.log("${body}")
			.to("produceramqp:" + queueName);
		
		// @formatter:on


	}

}
