package com.adeliosys.sample.service;

import com.adeliosys.sample.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
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

    public String chooseHero() {
        String request = """
                I'm the mayor of a medium-sized city.
                I received information that large mutant rats are about to attack the city.
                These rabid rats come from the sewers. They are about the size of a dog and there are hundreds of them.
                I need to hire a superhero to protect the city, but I can afford only one.
                From the next superheroes, describe the pros and cons of each one against the rats invasion.
                Then choose the one that you think is the best to protect the city and explain why you selected that superhero.
                """;

        // Add the heroes to the request
        List<String> heroes = List.of("hero1.pdf", "hero2.doc", "hero3.odt", "hero4.pptx", "hero5.html");
        for (int i = 0; i < heroes.size(); i++) {
            // Read the hero file using Tika
            Resource resource = new ClassPathResource("data/" + heroes.get(i));
            TikaDocumentReader tikaReader = new TikaDocumentReader(resource);
            List<Document> documents = tikaReader.get();
            String heroContent = documents.getFirst().getText();

            request += "\n\nSuperhero %d:\n%s\n\n".formatted(i + 1, heroContent);
        }

        return chatClient.prompt(request).call().content();
    }

    public List<Country> getMostPopulatedCountries() {
        String request = "List the 3 most populated countries. For each country, provide the name and the population in millions.";
        return chatClient.prompt(request).call()
                .entity(new ParameterizedTypeReference<>() {
                });
    }
}
