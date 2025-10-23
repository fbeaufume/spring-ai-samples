package com.adeliosys.sample;

import com.adeliosys.sample.service.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;
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
        long timestamp = System.currentTimeMillis();

        execute("Asking a question", () -> businessService.askQuestion("Tell me a programming joke"));

        execute("Telling a joke", () -> businessService.tellJoke("programming"));

        execute("Choosing the right hero", businessService::chooseHero);

        execute("Listing the most populated countries", businessService::getMostPopulatedCountries);

        LOGGER.info("Done in {} seconds", formatDuration(timestamp));
    }

    /**
     * Log the execution of an LLM call.
     */
    private void execute(String requestDescription, Supplier<Object> supplier) {
        try {
            long timestamp = System.currentTimeMillis();
            LOGGER.info(requestDescription);
            Object response = supplier.get();
            LOGGER.info("Response from the AI in {} seconds:\n{}", formatDuration(timestamp), response);
        } catch (Exception e) {
            LOGGER.warn("Error during the request", e);
        }
    }

    /**
     * Format in seconds the duration from a timestamp, e.g. '3.274'.
     */
    private String formatDuration(long timestamp) {
        return String.format(Locale.US, "%.3f", (System.currentTimeMillis() - timestamp) / 1000.0);
    }
}
