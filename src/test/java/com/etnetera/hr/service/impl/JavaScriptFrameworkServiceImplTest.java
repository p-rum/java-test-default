package com.etnetera.hr.service.impl;

import com.etnetera.hr.exception.JavaScriptFrameworkAlreadyExistsException;
import com.etnetera.hr.exception.JavaScriptFrameworkNotFoundException;
import com.etnetera.hr.mapper.JavaScriptFrameworkMapper;
import com.etnetera.hr.model.response.JavaScriptFrameworkResponse;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
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

import java.util.List;
import java.util.Optional;

import static com.etnetera.hr.utils.TestDataProvider.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaScriptFrameworkServiceImplTest {

    @Mock
    Logger log;
    @Mock
    JavaScriptFrameworkRepository repository;
    @Mock
    JavaScriptFrameworkMapper mapper;
    @InjectMocks
    JavaScriptFrameworkServiceImpl serviceUnderTest;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(serviceUnderTest);
    }

    @Test
    public void testGetAll() {
        var frameworks = List.of(FRAMEWORK);
        var responses = List.of(FRAMEWORK_RESPONSE);

        when(repository.findAll()).thenReturn(frameworks);
        when(mapper.mapListToResponse(frameworks)).thenReturn(responses);

        List<JavaScriptFrameworkResponse> result = serviceUnderTest.getAll();
        Assert.assertEquals(responses, result);
    }

    @Test
    public void testGetByName() {
        when(repository.findByName(FRAMEWORK_NAME)).thenReturn(FRAMEWORK);
        when(mapper.mapEntityToResponse(FRAMEWORK)).thenReturn(FRAMEWORK_RESPONSE);

        JavaScriptFrameworkResponse result = serviceUnderTest.getByName(FRAMEWORK_NAME);
        Assertions.assertEquals(FRAMEWORK_RESPONSE, result);
    }

    @Test
    public void testGetByName_returnsNothingWhenFrameworkNotFound() {
        when(repository.findByName(FRAMEWORK_NAME)).thenReturn(null);

        JavaScriptFrameworkResponse result = serviceUnderTest.getByName(FRAMEWORK_NAME);
        Assertions.assertNull(result);
    }

    @Test
    public void testGet() {
        when(repository.findById(FRAMEWORK_ID)).thenReturn(Optional.of(FRAMEWORK));
        when(mapper.mapEntityToResponse(FRAMEWORK)).thenReturn(FRAMEWORK_RESPONSE);

        JavaScriptFrameworkResponse result = serviceUnderTest.get(FRAMEWORK_ID);
        Assertions.assertEquals(FRAMEWORK_RESPONSE, result);
    }

    @Test
    public void testGet_whenNotFoundThenThrowJavaScriptFrameworkNotFoundException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(JavaScriptFrameworkNotFoundException.class, () -> serviceUnderTest.get(FRAMEWORK_ID));
    }


    @Test
    public void testUpdate() {
        var request = UPDATE_JAVA_SCRIPT_FRAMEWORK_REQUEST;
        var updated = UPDATED_FRAMEWORK;
        var updatedResponse = UPDATED_FRAMEWORK_RESPONSE;

        when(repository.findById(FRAMEWORK_ID)).thenReturn(Optional.of(FRAMEWORK));
        when(mapper.mapEntityToResponse(updated)).thenReturn(updatedResponse);
        when(mapper.update(FRAMEWORK, request)).thenReturn(updated);
        when(repository.save(updated)).thenReturn(updated);

        JavaScriptFrameworkResponse result = serviceUnderTest.update(FRAMEWORK_ID, request);
        Assertions.assertEquals(updatedResponse, result);
    }

    @Test
    public void testUpdate_nonExistingEntityShouldThrowJavaScriptFrameworkNotFoundException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(JavaScriptFrameworkNotFoundException.class, () -> serviceUnderTest.update(1L, UPDATE_JAVA_SCRIPT_FRAMEWORK_REQUEST));
    }

    @Test
    public void testCreate() {

        var entity = FRAMEWORK;
        entity.setVersions(List.of());
        entity.setVersions(List.of());
        entity.setId(FRAMEWORK_ID);

        final var newResponse = FRAMEWORK_RESPONSE;
        newResponse.setVersions(List.of());

        when(repository.existsByName(FRAMEWORK_NAME)).thenReturn(false);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.mapEntityToResponse(entity)).thenReturn(newResponse);
        when(mapper.mapToEntity(CREATE_JAVA_SCRIPT_FRAMEWORK_REQUEST)).thenReturn(entity);

        JavaScriptFrameworkResponse result = serviceUnderTest.create(CREATE_JAVA_SCRIPT_FRAMEWORK_REQUEST);
        Assertions.assertEquals(newResponse, result);
    }

    @Test
    public void testCreate_entityAlreadyExistsShouldThrowJavaScriptFrameworkAlreadyExistsException() {
        when(repository.existsByName(FRAMEWORK_NAME)).thenReturn(true);
        Assertions.assertThrows(JavaScriptFrameworkAlreadyExistsException.class, () -> serviceUnderTest.create(CREATE_JAVA_SCRIPT_FRAMEWORK_REQUEST));
    }
}

