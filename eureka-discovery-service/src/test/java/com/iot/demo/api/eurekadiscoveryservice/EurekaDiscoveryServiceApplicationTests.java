package com.iot.demo.api.eurekadiscoveryservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
	"eureka.client.enabled=false",
	"eureka.client.register-with-eureka=false",
	"eureka.client.fetch-registry=false",
	"spring.cloud.discovery.enabled=false",
	"eureka.server.enableSelfPreservation=false",
	"eureka.server.expectedClientRenewalIntervalSeconds=30",
	"eureka.dashboard.enabled=false",
	"server.port=0",
	"spring.main.allow-bean-definition-overriding=true"
}, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class EurekaDiscoveryServiceApplicationTests
{

	@Test
	void contextLoads() {
	}

}
