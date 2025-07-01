package com.aiservice.util;

import com.aiservice.dto.MessageDto;

import reactor.core.publisher.Mono;

public interface Chat {
	Mono<Object> handleChat(MessageDto message);
}