package com.quizo.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.quizo.app.util.Constants.MODELS;

@Service
public class ChatService {
    @Value("${llm.mistral.baseApiUrl")
    private String baseUrl;
    @Value("${llm.mistral.apiKey")
    private String apiKey;

    private RestTemplate httpClient = new RestTemplate();

    public Object chatCompletion(String content) {
        return null;
    }

    public Object getModels() {
        RequestEntity<Void> request = RequestEntity
            .get(baseUrl + MODELS)
            .header("Authorization", "Bearer " + apiKey)
            .build();

        return httpClient.exchange(baseUrl + MODELS, HttpMethod.GET, request, Object.class);
    }
}
