package com.etnetera.hr.controller;

import com.etnetera.hr.Application;
import com.etnetera.hr.service.JavaScriptFrameworkService;
import com.etnetera.hr.service.JavaScriptFrameworkVersionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JavaScriptFrameworkService.class, JavaScriptFrameworkVersionService.class, JavaScriptFrameworkController.class,
        Application.class})
@AutoConfigureMockMvc
public class JavaScriptFrameworkControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    JavaScriptFrameworkService frameworkService;
    @MockBean
    JavaScriptFrameworkVersionService versionService;
    @InjectMocks
    JavaScriptFrameworkController javaScriptFrameworkController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFrameworks() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/frameworks")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testGetFramework() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/frameworks/abcd")).andExpect(status().isBadRequest());
    }

    @Test
    public void testGetFrameworkVersions() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/frameworks/2/versions")).andDo(result -> print()).andExpect(status().isNotFound());
    }


    @Test
    public void testGetFrameworkVersion() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/frameworks/2/versions/1")).andDo(result -> print()).andExpect(status().isNotFound());
    }

}