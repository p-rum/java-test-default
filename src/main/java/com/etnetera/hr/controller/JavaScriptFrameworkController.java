package com.etnetera.hr.controller;

import com.etnetera.hr.exception.JavaScriptFrameworkNotFoundException;
import com.etnetera.hr.model.request.CreateJavaScriptFrameworkRequest;
import com.etnetera.hr.model.request.CreateJavaScriptFrameworkVersionRequest;
import com.etnetera.hr.model.request.UpdateJavaScriptFrameworkRequest;
import com.etnetera.hr.model.request.UpdateJavaScriptFrameworkVersionRequest;
import com.etnetera.hr.model.response.JavaScriptFrameworkResponse;
import com.etnetera.hr.model.response.JavaScriptFrameworkVersionResponse;
import com.etnetera.hr.service.JavaScriptFrameworkService;
import com.etnetera.hr.service.JavaScriptFrameworkVersionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Simple REST controller for accessing application logic.
 *
 * @author Etnetera
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class JavaScriptFrameworkController {

    private final JavaScriptFrameworkService frameworkService;
    private final JavaScriptFrameworkVersionService versionService;

    @GetMapping("/frameworks")
    public List<JavaScriptFrameworkResponse> frameworks() {
        return frameworkService.getAll();
    }

    @GetMapping("/frameworks/{id}")
    public JavaScriptFrameworkResponse getFramework(@PathVariable Long id) {
        return frameworkService.get(id);
    }

    @GetMapping("/frameworks/{id}/versions")
    public List<JavaScriptFrameworkVersionResponse> getFrameworkVersions(@PathVariable Long id) {
        checkFrameworkExists(id);
        return versionService.listByFrameworkId(id);
    }

    @GetMapping("/frameworks/search")
    public JavaScriptFrameworkResponse getFramework(@RequestParam String name) {
        return frameworkService.getByName(name);
    }

    @PostMapping("/frameworks")
    @ResponseStatus(HttpStatus.CREATED)
    public JavaScriptFrameworkResponse createFramework(@RequestBody CreateJavaScriptFrameworkRequest request) {
        return frameworkService.create(request);
    }

    @PutMapping("/frameworks/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public JavaScriptFrameworkResponse updateFramework(@PathVariable Long id, @RequestBody UpdateJavaScriptFrameworkRequest request) {
        return frameworkService.update(id, request);
    }

    @DeleteMapping("/frameworks/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteFramework(@PathVariable Long id) {
        frameworkService.delete(id);
    }

    @PostMapping("/frameworks/{id}/versions")
    @ResponseStatus(HttpStatus.CREATED)
    public JavaScriptFrameworkVersionResponse createFrameworkVersion(@PathVariable Long id, @RequestBody CreateJavaScriptFrameworkVersionRequest request) {
        checkFrameworkExists(id);
        return versionService.create(request, id);
    }

    @PutMapping("/frameworks/{id}/versions/{versionId}")
    @ResponseStatus(HttpStatus.OK)
    public JavaScriptFrameworkVersionResponse updateFrameworkVersion(@PathVariable Long id, @RequestBody UpdateJavaScriptFrameworkVersionRequest request, @PathVariable Long versionId) {
        checkFrameworkExists(id);
        return versionService.update(request, versionId);
    }

    @GetMapping("/frameworks/{id}/versions/{versionId}")
    @ResponseStatus(HttpStatus.OK)
    public JavaScriptFrameworkVersionResponse getFrameworkVersion(@PathVariable Long id, @PathVariable Long versionId) {
        checkFrameworkExists(id);
        return versionService.get(versionId);
    }

    @DeleteMapping("/frameworks/{id}/versions/{versionId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteFrameworkVersion(@PathVariable Long id, @PathVariable Long versionId) {
        checkFrameworkExists(id);
        versionService.delete(versionId);
    }


    private void checkFrameworkExists(Long id) {
        if (!frameworkService.existsById(id)) {
            log.error("JavaScript framework with id: {} not found.", id);
            throw new JavaScriptFrameworkNotFoundException(id);
        }
    }
}
