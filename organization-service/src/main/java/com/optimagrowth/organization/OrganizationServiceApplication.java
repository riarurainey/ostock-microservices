package com.optimagrowth.organization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@RefreshScope
@EnableEurekaClient
@EnableBinding(Source.class)
@SpringBootApplication
public class OrganizationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganizationServiceApplication.class, args);
    }

}
