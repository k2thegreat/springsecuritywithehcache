package com.k2tech.ehcacheprac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.k2tech.ehcacheprac"})
public class EhCachePracApplication {

    public static void main(String[] args) {
        SpringApplication.run(EhCachePracApplication.class, args);
    }

}
