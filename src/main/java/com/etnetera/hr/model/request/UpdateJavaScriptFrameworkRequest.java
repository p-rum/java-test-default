package com.etnetera.hr.model.request;

import com.etnetera.hr.enumeration.HypeLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class UpdateJavaScriptFrameworkRequest {
    @NotBlank
    private HypeLevel hypeLevel;
}
