package com.etnetera.hr.service;

import com.etnetera.hr.model.request.CreateJavaScriptFrameworkVersionRequest;
import com.etnetera.hr.model.request.UpdateJavaScriptFrameworkVersionRequest;
import com.etnetera.hr.model.response.JavaScriptFrameworkVersionResponse;

import java.util.List;

public interface JavaScriptFrameworkVersionService {

    JavaScriptFrameworkVersionResponse create(CreateJavaScriptFrameworkVersionRequest request, Long javaScriptFrameworkId);

    JavaScriptFrameworkVersionResponse update(UpdateJavaScriptFrameworkVersionRequest request, Long id);

    void delete(Long id);

    JavaScriptFrameworkVersionResponse get(Long javaScriptFrameworkId, String version);

    JavaScriptFrameworkVersionResponse get(Long id);

    List<JavaScriptFrameworkVersionResponse> listByFrameworkId(Long frameworkId);
}
