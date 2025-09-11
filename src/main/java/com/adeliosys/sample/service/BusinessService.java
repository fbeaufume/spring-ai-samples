package com.adeliosys.sample.service;

import com.adeliosys.sample.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BusinessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessService.class);

    @Autowired
    private ChatClient chatClient;

    @Value("classpath:templates/joke-prompt.st")
    private Resource jokePrompt;

    public String askQuestion(String question) {
        ChatResponse chatResponse = chatClient.prompt(question).call().chatResponse();

        Usage usage = chatResponse.getMetadata().getUsage();
        LOGGER.info("Used {} tokens for this request ({} for prompt + {} for response generation)",
                usage.getTotalTokens(), usage.getPromptTokens(), usage.getCompletionTokens());

        return chatResponse.getResult().getOutput().getText();
    }

    public String tellJoke(String subject) {
        PromptTemplate template = new PromptTemplate(jokePrompt);
        Prompt prompt = template.create(Map.of("subject", subject));

        return chatClient.prompt(prompt).call().content();
    }

    public List<Country> getMostPopulatedCountries() {
        String request = "List the 3 most populated countries. For each country, provide the name and the population in millions.";
        return chatClient.prompt(request).call()
                .entity(new ParameterizedTypeReference<>() {
                });
    }
}
