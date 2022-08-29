package com.etnetera.hr.model.request;

import com.etnetera.hr.enumeration.HypeLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateJavaScriptFrameworkRequest implements Serializable {

    @NotBlank
    @Max(30)
    private String name;
    private HypeLevel hypeLevel;
}
