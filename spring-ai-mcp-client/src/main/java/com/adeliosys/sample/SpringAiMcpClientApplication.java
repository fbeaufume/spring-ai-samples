package com.adeliosys.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAiMcpClientApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringAiMcpClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringAiMcpClientApplication.class, args);
    }

    @Autowired
    private ChatClient chatClient;

    @Override
    public void run(String... args) {
        LOGGER.info("Response from the AI: {}",
                chatClient.prompt("What is the current weather in Paris ?").call().content());
    }
}
