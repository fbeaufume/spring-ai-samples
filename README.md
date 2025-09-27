# Spring AI Basics

This repository is a sample application for my
[Getting started with Spring AI](https://www.adeliosys.fr/articles/spring-ai-basics/) article.

It is implemented in Java with Spring Boot, and takes the form of a simple command line application.

By default, it requires Ollama with the Llama 3.1 model, so first install Ollama then run `ollama pull llama3.1:8b`
to install the model.

If you want to use another Ollama model, update `application.properties`. I added some examples.

If you want to use another LLM provider (OpenAI, Mistral, etc.) use the right starter in `pom.xml`,
then declare the specific configuration (such as the API key) in `application.properties`.
See [Spring AI reference documentation](https://docs.spring.io/spring-ai/reference/) for more details.

Start the application with `mvnw spring-boot:run` or from your IDE.

Notable source files:

- `pom.xml` declares the dependency management for Spring AI as well as the right starters.
- `application.properties` contains the configuration for Spring AI and Ollama.
- `AiConfig.java` defines the AI beans used by other components.
- `BusinessService.java` uses the LLM to implement various tasks.
- `SpringAiBAsicsApplication.java` is the main class and calls the methods from `BusinessService`.
