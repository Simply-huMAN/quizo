package com.quizo.app.response;

import com.quizo.app.dto.Error;
import com.quizo.app.dto.Metadata;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response<T> {
    private Metadata metadata;
    private T data;
    private Error error;
}
