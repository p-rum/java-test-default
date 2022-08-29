package com.etnetera.hr.model.response;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;


@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements Serializable {

    private ErrorMessage errorMessage;

    @Builder
    @ToString
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorMessage implements Serializable {

        private final Instant date = Instant.now();
        private String title;
        private String description;
    }
}
