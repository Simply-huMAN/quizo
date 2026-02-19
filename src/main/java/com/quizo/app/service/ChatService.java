package com.quizo.app.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizo.app.dto.FormDTO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.mistralai.MistralAiChatOptions;
import org.springframework.ai.mistralai.api.MistralAiApi;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Service
public class ChatService {
    private final ChatModel chatModel;
    private final ChatClient chatClient;

    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
        this.chatClient = ChatClient.builder(chatModel).build();
    }

    public String chat(String prompt) {
        return chatModel.call(prompt);
    }

    public FormDTO createForm(String topic) {
        FormDTO response = chatClient.prompt().user(topic).call().entity(FormDTO.class);
        return response;
    }

    public Object createQuiz(String topic) throws IOException {
        ChatResponse response = chatModel.call(
                new Prompt(topic,
                        MistralAiChatOptions.builder()
                                    .responseFormat(
                                    new MistralAiApi.ChatCompletionRequest.ResponseFormat("json_schema",
                                            loadQuizSchema()))
                                .build())
        );

        // return response;
        return response.getResult().getOutput().getText();
    }

    public String getModels() {
        return "";
    }

    private Map<String, Object> loadQuizSchema() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = new ClassPathResource("static/QuizSchema.json").getInputStream()) {
            return mapper.readValue(is, new TypeReference<>() {
            });
        }
    }
}
