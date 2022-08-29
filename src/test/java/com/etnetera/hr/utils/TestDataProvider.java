package com.etnetera.hr.utils;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.data.JavaScriptFrameworkVersion;
import com.etnetera.hr.enumeration.HypeLevel;
import com.etnetera.hr.model.request.CreateJavaScriptFrameworkRequest;
import com.etnetera.hr.model.request.CreateJavaScriptFrameworkVersionRequest;
import com.etnetera.hr.model.request.UpdateJavaScriptFrameworkRequest;
import com.etnetera.hr.model.request.UpdateJavaScriptFrameworkVersionRequest;
import com.etnetera.hr.model.response.JavaScriptFrameworkResponse;
import com.etnetera.hr.model.response.JavaScriptFrameworkVersionResponse;

import java.time.Instant;
import java.util.List;

public class TestDataProvider {

    public static final String FRAMEWORK_VERSION = "1.0.0";
    public static final String FRAMEWORK_NAME = "React";
    public static final long FRAMEWORK_ID = 1L;
    public static final long VERSION_ID = 1L;
    public static final HypeLevel HYPE_LEVEL = HypeLevel.MEDIUM;
    public static final HypeLevel UPDATED_HYPE_LEVEL = HypeLevel.HIGH;
    public static final Instant DEPRECATION_DATE = Instant.MAX;
    public static final Instant UPDATED_DEPRECATION_DATE = Instant.MIN;

    public static final JavaScriptFrameworkVersion VERSION = JavaScriptFrameworkVersion.builder().javaScriptFrameworkId(FRAMEWORK_ID).version(FRAMEWORK_VERSION).id(VERSION_ID).deprecationDate(DEPRECATION_DATE).build();
    public static final List<JavaScriptFrameworkVersion> VERSIONS = List.of(VERSION);
    public static final JavaScriptFramework FRAMEWORK = new JavaScriptFramework(FRAMEWORK_ID, FRAMEWORK_NAME, HYPE_LEVEL, VERSIONS);
    public static final JavaScriptFramework UPDATED_FRAMEWORK = new JavaScriptFramework(FRAMEWORK_ID, FRAMEWORK_NAME, UPDATED_HYPE_LEVEL, VERSIONS);
    public static final JavaScriptFrameworkVersionResponse VERSION_RESPONSE = JavaScriptFrameworkVersionResponse.builder()
                                                                                                                .deprecationDate(DEPRECATION_DATE).id(VERSION_ID).version(FRAMEWORK_VERSION).build();
    public static List<JavaScriptFrameworkVersionResponse> VERSIONS_RESPONSE = List.of(VERSION_RESPONSE);
    public static final JavaScriptFrameworkResponse FRAMEWORK_RESPONSE = JavaScriptFrameworkResponse
            .builder()
            .hypeLevel(HYPE_LEVEL)
            .id(FRAMEWORK_ID)
            .name(FRAMEWORK_NAME)
            .versions(VERSIONS_RESPONSE)
            .build();
    public static final JavaScriptFrameworkResponse UPDATED_FRAMEWORK_RESPONSE = JavaScriptFrameworkResponse
            .builder()
            .hypeLevel(UPDATED_HYPE_LEVEL)
            .id(FRAMEWORK_ID)
            .name(FRAMEWORK_NAME)
            .versions(VERSIONS_RESPONSE)
            .build();
    public static final CreateJavaScriptFrameworkVersionRequest CREATE_JAVA_SCRIPT_FRAMEWORK_VERSION_REQUEST = CreateJavaScriptFrameworkVersionRequest.builder().version(FRAMEWORK_VERSION).deprecationDate(DEPRECATION_DATE).build();
    public static final CreateJavaScriptFrameworkRequest CREATE_JAVA_SCRIPT_FRAMEWORK_REQUEST = CreateJavaScriptFrameworkRequest.builder().name(FRAMEWORK_NAME).hypeLevel(HYPE_LEVEL).build();
    public static final UpdateJavaScriptFrameworkRequest UPDATE_JAVA_SCRIPT_FRAMEWORK_REQUEST = UpdateJavaScriptFrameworkRequest.builder()
                                                                                                                                .hypeLevel(UPDATED_HYPE_LEVEL).build();
    public static final UpdateJavaScriptFrameworkVersionRequest UPDATE_JAVA_SCRIPT_FRAMEWORK_VERSION_REQUEST = UpdateJavaScriptFrameworkVersionRequest
            .builder()
            .deprecationDate(UPDATED_DEPRECATION_DATE)
            .build();
    public static final JavaScriptFrameworkVersionResponse UPDATED_FRAMEWORK_VERSION_RESPONSE = JavaScriptFrameworkVersionResponse.builder()
                                                                                                                                  .version(FRAMEWORK_VERSION)
                                                                                                                                  .deprecationDate(UPDATED_DEPRECATION_DATE)
                                                                                                                                  .id(VERSION_ID)
                                                                                                                                  .build();


}