package com.example.hello.world;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableEurekaClient
@RestController
@RibbonClient(name = "hello-world-service")
@Slf4j
@EnableHystrixDashboard
@EnableCircuitBreaker
public class HelloWorldServiceApplication {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/hi")
    @HystrixCommand(fallbackMethod = "notAvailable")
    public String hi() throws UnknownHostException {
        log.info("Request on " + InetAddress.getLocalHost().getHostAddress());
        String greeting = restTemplate().getForObject("http://hello-service/hello/world", String.class);
        return greeting;
    }

    private String notAvailable() {
        return "Hello Service Not available";
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldServiceApplication.class, args);
    }
}
