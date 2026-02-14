package com.quizo.app.response;

import com.quizo.app.dto.Question;

import java.util.List;

public class QuizResponse {
    private String title;
    private List<Question> questions;
    private List<String> tags;
}
