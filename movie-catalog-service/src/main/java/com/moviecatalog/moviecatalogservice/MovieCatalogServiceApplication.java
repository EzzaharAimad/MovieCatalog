package com.moviecatalog.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MovieCatalogServiceApplication {

	@Bean //A PRODUCER, it tells spring execute this method and save this instance somwhere waiting for autowired (The consumer) to ask for it
	//The time of bean's execution may be different from eager to lazy, but it executes only once cuz it's singleton by default
	//lazy initialization (when it's configured) : when an autowired is detected spring fetch an instance of the autowired method, then execute the bean that returns the same type once the bean is found
	//by default it's eager initialization : all the defined beans, and their dependencies are created when the application context is created.
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@Bean
	public WebClient.Builder getWebClientBuilder (){
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
