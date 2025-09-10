package com.adeliosys.sample.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BusinessService {

    @Autowired
    private ChatClient chatClient;

    public String askQuestion(String question) {
        return chatClient.prompt(question).call().content();
    }

    public String tellJoke(String subject) {
        PromptTemplate template = new PromptTemplate("Tell me a joke about {subject}");
        Prompt prompt = template.create(Map.of("subject", subject));

        return chatClient.prompt(prompt).call().content();
    }
}
