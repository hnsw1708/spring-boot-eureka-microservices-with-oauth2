package com.iot.demo.api.eurekadiscoveryservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
  properties = {
    "eureka.client.enabled=false",
    "eureka.client.register-with-eureka=false",
    "eureka.client.fetch-registry=false",
    "eureka.server.enable-self-preservation=false",
    "spring.main.allow-bean-definition-overriding=true"
  }
)
class EurekaDiscoveryServiceApplicationTests
{

	@Test
	void contextLoads() {
	}

}
