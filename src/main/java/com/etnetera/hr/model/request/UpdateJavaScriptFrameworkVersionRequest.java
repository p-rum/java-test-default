package com.etnetera.hr.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@Validated
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateJavaScriptFrameworkVersionRequest {

    @Valid
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Instant deprecationDate;
}
