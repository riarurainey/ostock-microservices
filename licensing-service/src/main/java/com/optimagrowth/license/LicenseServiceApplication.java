package com.optimagrowth.license;

import com.optimagrowth.license.events.model.OrganizationChangeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@RefreshScope
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@EnableBinding(Sink.class)
public class LicenseServiceApplication {


    private static final Logger logger = LoggerFactory.getLogger(LicenseServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LicenseServiceApplication.class, args);
    }


    @StreamListener(Sink.INPUT)
    public void loggerSink(OrganizationChangeModel orgChange) {
        logger.debug("Received {} event for the organization id {}", orgChange.getAction(), orgChange.getOrganizationId());
    }

}