package com.adeliosys.sample;

import com.adeliosys.sample.service.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.function.Supplier;

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
        execute("Asking a question", () -> businessService.askQuestion("Tell me a programming joke"));

        execute("Telling a joke", () -> businessService.tellJoke("programming"));

        execute("Choosing the right hero", businessService::chooseHero);

        execute("Listing the most populated countries", businessService::getMostPopulatedCountries);
    }

    private void execute(String requestDescription, Supplier<Object> supplier) {
        LOGGER.info(requestDescription);
        LOGGER.info("Response from the AI:\n{}", supplier.get());
    }
}
