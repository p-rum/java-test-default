package com.etnetera.hr.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JavaScriptFrameworkVersionResponse {
    private Long id;
    private String version;
    private Instant deprecationDate;
}
