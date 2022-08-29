package com.etnetera.hr.service.impl;

import com.etnetera.hr.exception.JavaScriptFrameworkVersionAlreadyExistsException;
import com.etnetera.hr.exception.JavaScriptFrameworkVersionNotFoundException;
import com.etnetera.hr.mapper.JavaScriptFrameworkMapper;
import com.etnetera.hr.model.request.CreateJavaScriptFrameworkVersionRequest;
import com.etnetera.hr.model.request.UpdateJavaScriptFrameworkVersionRequest;
import com.etnetera.hr.model.response.JavaScriptFrameworkVersionResponse;
import com.etnetera.hr.repository.JavaScriptFrameworkVersionRepository;
import com.etnetera.hr.service.JavaScriptFrameworkVersionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class JavaScriptFrameworkVersionServiceImpl implements JavaScriptFrameworkVersionService {

    private final JavaScriptFrameworkVersionRepository repository;
    private final JavaScriptFrameworkMapper mapper;

    @Override
    public JavaScriptFrameworkVersionResponse create(CreateJavaScriptFrameworkVersionRequest request, Long javaScriptFrameworkId) {
        if (repository.existsByJavaScriptFrameworkIdAndVersion(javaScriptFrameworkId, request.getVersion())) {
            throw new JavaScriptFrameworkVersionAlreadyExistsException(javaScriptFrameworkId, request.getVersion());
        }
        return mapper.mapVersionToResponse(repository.save(mapper.mapVersionRequestToEntity(request, javaScriptFrameworkId)));
    }

    @Override
    public JavaScriptFrameworkVersionResponse update(UpdateJavaScriptFrameworkVersionRequest request, Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new JavaScriptFrameworkVersionNotFoundException(id));
        entity.setDeprecationDate(request.getDeprecationDate());
        return mapper.mapVersionToResponse(repository.save(entity));
    }


    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public JavaScriptFrameworkVersionResponse get(Long javaScriptFrameworkId, String version) {
        return mapper.mapVersionToResponse(repository.findByJavaScriptFrameworkIdAndVersion(javaScriptFrameworkId, version));
    }

    @Override
    public JavaScriptFrameworkVersionResponse get(Long id) {
        return mapper.mapVersionToResponse(repository.findById(id).orElseThrow(() -> new JavaScriptFrameworkVersionNotFoundException(id)));
    }

    @Override
    public List<JavaScriptFrameworkVersionResponse> listByFrameworkId(Long frameworkId) {
        return repository.findAllByJavaScriptFrameworkIdOrderByDeprecationDateDesc(frameworkId).stream().map(mapper::mapVersionToResponse).collect(Collectors.toList());
    }

}
