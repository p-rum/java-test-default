package com.etnetera.hr.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@Validated
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateJavaScriptFrameworkVersionRequest {

    @NotBlank
    private String version;

    @NotBlank
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Instant deprecationDate;
}
