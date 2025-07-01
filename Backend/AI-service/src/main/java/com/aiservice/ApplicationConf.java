package com.aiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.aiservice.util.Chat;
import com.aiservice.util.ChatAdapter;
import com.aiservice.util.ChatApplication;

public class ApplicationConf {

	@Bean
	Chat chat(@Autowired ChatAdapter chatAdapter) {
		return new ChatApplication(chatAdapter);
	}

}
