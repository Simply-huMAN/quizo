package com.quizo.app.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatRequestBody {

    private String content;

    // No-args constructor (needed for Jackson in some setups)
    public ChatRequestBody() {
    }

    // Constructor for Jackson deserialization
    @JsonCreator
    public ChatRequestBody(@JsonProperty("content") String content) {
        this.content = content;
    }

    // Getter
    public String getContent() {
        return content;
    }

    // Setter
    public void setContent(String content) {
        this.content = content;
    }
}
