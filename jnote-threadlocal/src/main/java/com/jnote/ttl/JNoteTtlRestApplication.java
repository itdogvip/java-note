package com.jnote.ttl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class JNoteTtlRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(JNoteTtlRestApplication.class, args);
    }
}
