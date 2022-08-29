package com.etnetera.hr.model.response;


import com.etnetera.hr.enumeration.HypeLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JavaScriptFrameworkResponse {


    private Long id;
    private String name;
    private HypeLevel hypeLevel;
    @Builder.Default
    private List<JavaScriptFrameworkVersionResponse> versions = new ArrayList<>();

}
