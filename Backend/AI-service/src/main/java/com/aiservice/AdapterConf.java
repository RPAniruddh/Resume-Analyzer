package com.aiservice;

import static dev.langchain4j.model.huggingface.HuggingFaceModelName.TII_UAE_FALCON_7B_INSTRUCT;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.aiservice.util.ChatAdapter;
import com.aiservice.util.ChatLangChainAdapter;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.huggingface.HuggingFaceChatModel;

public class AdapterConf {

	@Value("${application.ai.huggingface.chat.api-key}")
	String huggingFaceAccessToken;

	@Bean
	ChatLanguageModel chatLanguageModel() {
		return HuggingFaceChatModel.builder().accessToken(huggingFaceAccessToken).modelId(TII_UAE_FALCON_7B_INSTRUCT)
				.timeout(Duration.ofSeconds(15)).temperature(0.7).maxNewTokens(20).waitForModel(true).build();
	}

	@Bean
	ChatAdapter chatLangChainAdapter(@Autowired ChatLanguageModel chatLanguageModel) {
		return new ChatLangChainAdapter(chatLanguageModel);
	}
}