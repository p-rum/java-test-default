package com.etnetera.hr.service.impl;

import com.etnetera.hr.exception.JavaScriptFrameworkVersionAlreadyExistsException;
import com.etnetera.hr.exception.JavaScriptFrameworkVersionNotFoundException;
import com.etnetera.hr.mapper.JavaScriptFrameworkMapper;
import com.etnetera.hr.model.response.JavaScriptFrameworkVersionResponse;
import com.etnetera.hr.repository.JavaScriptFrameworkVersionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.etnetera.hr.utils.TestDataProvider.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaScriptFrameworkVersionServiceImplTest {

    @Mock
    Logger log;
    @Mock
    JavaScriptFrameworkVersionRepository repository;
    @Mock
    JavaScriptFrameworkMapper mapper;
    @InjectMocks
    JavaScriptFrameworkVersionServiceImpl serviceUnderTest;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(serviceUnderTest);
    }


    @Test
    public void testGet() {
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(VERSION));
        when(repository.findByJavaScriptFrameworkIdAndVersion(FRAMEWORK_ID, FRAMEWORK_VERSION)).thenReturn(VERSION);
        when(mapper.mapVersionToResponse(VERSION)).thenReturn(VERSION_RESPONSE);
        JavaScriptFrameworkVersionResponse result = serviceUnderTest.get(VERSION_ID);
        Assert.assertEquals(VERSION_RESPONSE, result);
    }


    @Test
    public void testGet_returnsNothingWhenFrameworkNotFound() {
        when(repository.findByJavaScriptFrameworkIdAndVersion(FRAMEWORK_ID, FRAMEWORK_VERSION)).thenReturn(null);

        JavaScriptFrameworkVersionResponse result = serviceUnderTest.get(FRAMEWORK_ID, FRAMEWORK_VERSION);
        Assertions.assertNull(result);
    }

    @Test
    public void testGet_whenNotFoundThenThrowJavaScriptFrameworkVersionNotFoundException() {
        when(repository.findById(VERSION_ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(JavaScriptFrameworkVersionNotFoundException.class, () -> serviceUnderTest.get(VERSION_ID));
    }


    @Test
    public void testUpdate_nonExistingEntityShouldThrowJavaScriptFrameworkVersionNotFoundException() {
        when(repository.findById(VERSION_ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(JavaScriptFrameworkVersionNotFoundException.class, () -> serviceUnderTest.update(UPDATE_JAVA_SCRIPT_FRAMEWORK_VERSION_REQUEST, VERSION_ID));
    }

    @Test
    public void testCreate() {

        when(repository.existsByJavaScriptFrameworkIdAndVersion(FRAMEWORK_ID, FRAMEWORK_VERSION)).thenReturn(false);
        when(repository.save(VERSION)).thenReturn(VERSION);
        when(mapper.mapVersionToResponse(VERSION)).thenReturn(VERSION_RESPONSE);
        when(mapper.mapVersionRequestToEntity(CREATE_JAVA_SCRIPT_FRAMEWORK_VERSION_REQUEST, FRAMEWORK_ID)).thenReturn(VERSION);

        JavaScriptFrameworkVersionResponse result = serviceUnderTest.create(CREATE_JAVA_SCRIPT_FRAMEWORK_VERSION_REQUEST, VERSION_ID);
        Assertions.assertEquals(VERSION_RESPONSE, result);
    }

    public void testCreate_entityAlreadyExistsShouldThrowJavaScriptFrameworkVersionAlreadyExistsException() {
        when(repository.existsByJavaScriptFrameworkIdAndVersion(FRAMEWORK_ID, FRAMEWORK_VERSION)).thenReturn(true);
        Assertions.assertThrows(JavaScriptFrameworkVersionAlreadyExistsException.class, () -> serviceUnderTest.create(CREATE_JAVA_SCRIPT_FRAMEWORK_VERSION_REQUEST, FRAMEWORK_ID));
    }

    @Test
    public void testUpdate() {
        var updated = VERSION;
        updated.setDeprecationDate(UPDATED_DEPRECATION_DATE);

        when(repository.findById(VERSION_ID)).thenReturn(Optional.of(VERSION));
        when(mapper.mapVersionToResponse(updated)).thenReturn(UPDATED_FRAMEWORK_VERSION_RESPONSE);
        when(mapper.updateVersion(VERSION, UPDATE_JAVA_SCRIPT_FRAMEWORK_VERSION_REQUEST)).thenReturn(updated);
        when(repository.save(updated)).thenReturn(updated);

        JavaScriptFrameworkVersionResponse result = serviceUnderTest.update(UPDATE_JAVA_SCRIPT_FRAMEWORK_VERSION_REQUEST, FRAMEWORK_ID);
        Assertions.assertEquals(UPDATED_FRAMEWORK_VERSION_RESPONSE, result);
    }
}

