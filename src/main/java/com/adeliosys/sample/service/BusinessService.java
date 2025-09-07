package com.adeliosys.sample.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    @Autowired
    private ChatClient chatClient;

    public String askQuestion(String question) {
        return chatClient.prompt(question).call().content();
    }
}
