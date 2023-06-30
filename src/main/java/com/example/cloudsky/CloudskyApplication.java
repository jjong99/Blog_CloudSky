package com.example.cloudsky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CloudskyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudskyApplication.class, args);
    }

}
