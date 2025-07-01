package com.aiservice;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;


import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.aiservice.dto.MessageDto;
import com.aiservice.util.Chat;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Log4j2
public class AdapterRestApiConf {

	@NonNull
	Chat chat;

	@Bean
	RouterFunction<ServerResponse> composedNotifyRoutes() {
		return route().POST("/chat", accept(MediaType.APPLICATION_JSON), this::createClaim).build();
	}
	

	@Bean
	RouterFunction<ServerResponse> testRoute() {
	    return route(GET("/chat"), req -> ServerResponse.ok().bodyValue("Chat endpoint is up!"));
	}


	Mono<ServerResponse> createClaim(ServerRequest request) {
		log.info("Request received");

		return request.bodyToMono(MessageDto.class)
				.doOnNext(n -> log.info("Received request payload [messageDto={}]", n)).flatMap(chat::handleChat)
				.doOnNext(it -> log.info("Chat responded [chat={}]", it))
				.flatMap(c -> ServerResponse.ok().bodyValue(c));
	}

}