package com.example.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@Slf4j
public class HelloController {

    @Value("${greeting}")
    private String greeting;

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable String name) throws UnknownHostException {
        log.info("processing request on " + InetAddress.getLocalHost());
        return String.format("%s, %s", greeting, name);
    }
}
