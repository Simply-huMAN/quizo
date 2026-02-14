package com.quizo.app.dto;

import java.util.List;

public class Error {
    private String message;
    private List<Details> details;
}

class Details {
    private String field;
    private String issue;
}
