package com.etnetera.hr.service;


import com.etnetera.hr.model.request.CreateJavaScriptFrameworkRequest;
import com.etnetera.hr.model.request.UpdateJavaScriptFrameworkRequest;
import com.etnetera.hr.model.response.JavaScriptFrameworkResponse;

import javax.validation.constraints.NotNull;
import java.util.List;


public interface JavaScriptFrameworkService {


    List<JavaScriptFrameworkResponse> getAll();

    JavaScriptFrameworkResponse getByName(String name);

    JavaScriptFrameworkResponse get(Long id);

    void delete(Long id);

    JavaScriptFrameworkResponse update(Long id, UpdateJavaScriptFrameworkRequest request);

    JavaScriptFrameworkResponse create(CreateJavaScriptFrameworkRequest request);

    boolean existsById(@NotNull Long id);
}
