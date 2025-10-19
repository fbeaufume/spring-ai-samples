package com.adeliosys.sample.api;

import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Basic operations on chat conversations: list conversations, view a conversation, delete a conversation.
 */
@RestController
@RequestMapping("/conversations")
public class ConversationController {

    @Autowired
    private ChatMemoryRepository chatMemoryRepository;

    /**
     * Return the IDs of the stored conversations.
     */
    @GetMapping()
    public List<String> listConversationIds() {
        return chatMemoryRepository.findConversationIds();
    }

    /**
     * View the content of a given conversation.
     */
    @GetMapping("/{id}")
    public List<Message> viewConversation(@PathVariable String id) {
        return chatMemoryRepository.findByConversationId(id);
    }

    /**
     * Clear a given conversation.
     */
    @DeleteMapping("/{id}")
    public void deleteConversation(@PathVariable String id) {
        chatMemoryRepository.deleteByConversationId(id);
    }
}
