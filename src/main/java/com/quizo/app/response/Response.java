package com.quizo.app.response;

import com.quizo.app.dto.Error;
import com.quizo.app.dto.Metadata;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class Response<T> {
    private Metadata metadata;
    private T data;
    private Error error;
}

//public static <T> Response<T> success(T data) {
//    return Response.<T>builder()
//            .metadata(Metadata.builder().message("success").pagination(null).timestamp(Instant.now()).build())
//            .data(data)
//            .build();
//}
