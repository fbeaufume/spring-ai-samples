package com.adeliosys.sample.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller handles chat requests from the index.html chat page.
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private ChatClient chatClient;

    /**
     * Process one chat request.
     */
    @GetMapping
    public String getResponse(
            @RequestParam String request,
            @RequestParam(name = "conversation") String conversationId) {
        long timestamp = System.currentTimeMillis();
        try {
            return chatClient.prompt(request)
                    .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                    .call().content();
        } finally {
            LOGGER.info("Processed chat request '{}' in {} ms", request, System.currentTimeMillis() - timestamp);
        }
    }
}
