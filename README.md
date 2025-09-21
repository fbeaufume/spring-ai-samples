# Spring AI Basics

This repository is a sample application for my
[Getting started with Spring AI](https://www.adeliosys.fr/articles/spring-ai-basics/) article.

It is implemented in Java with Spring Boot, and takes the form of a simple command line application.

It requires Ollama with the Llama 3.1 model, so first install Ollama then run `ollama pull llama3.1:8b`
to install the model.

Of course, other LLMs can be used. To do so, update accordingly the starter in the `pom.xml` file
and the configuration in `application.properties`.

Start the application with `mvnw spring-boot:run` or from your IDE.

Notable source files:

- `pom.xml` declares the dependency management for Spring AI as well as the right starters.
- `application.properties` contains the configuration for Spring AI and Ollama.
- `AiConfig.java` defines the AI beans used by other components.
- `BusinessService.java` uses the LLM to implement various tasks.
- `SpringAiBAsicsApplication.java` is the main class and calls the methods from `BusinessService`.
