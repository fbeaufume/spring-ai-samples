package com.adeliosys.sample;

import com.adeliosys.sample.service.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAiBasicsApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringAiBasicsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringAiBasicsApplication.class, args);
    }

    @Autowired
    private BusinessService businessService;

    @Override
    public void run(String... args) {
        LOGGER.info("Telling a joke");
        String joke = businessService.askQuestion("Tell me a programming joke");
        LOGGER.info("Response from the AI:\n{}", joke);
    }
}
