package com.aiservice.util;

import com.aiservice.dto.MessageDto;

import reactor.core.publisher.Mono;

public interface ChatAdapter {
	Mono<Object> chatPrompt(MessageDto message);
}