package com.etnetera.hr.service.impl;

import com.etnetera.hr.exception.JavaScriptFrameworkAlreadyExistsException;
import com.etnetera.hr.exception.JavaScriptFrameworkNotFoundException;
import com.etnetera.hr.mapper.JavaScriptFrameworkMapper;
import com.etnetera.hr.model.request.CreateJavaScriptFrameworkRequest;
import com.etnetera.hr.model.request.UpdateJavaScriptFrameworkRequest;
import com.etnetera.hr.model.response.JavaScriptFrameworkResponse;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import com.etnetera.hr.service.JavaScriptFrameworkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JavaScriptFrameworkServiceImpl implements JavaScriptFrameworkService {

    private final JavaScriptFrameworkRepository repository;
    private final JavaScriptFrameworkMapper mapper;


    @Override
    public List<JavaScriptFrameworkResponse> getAll() {
        return mapper.mapListToResponse(repository.findAll());
    }

    @Override
    public JavaScriptFrameworkResponse getByName(String name) {
        return mapper.mapEntityToResponse(repository.findByName(name));
    }

    @Override
    public JavaScriptFrameworkResponse get(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new JavaScriptFrameworkNotFoundException(id));
        return mapper.mapEntityToResponse(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public JavaScriptFrameworkResponse update(Long id, UpdateJavaScriptFrameworkRequest request) {
        log.info("Updating framework with id: {}", id);
        var framework = repository.findById(id).orElseThrow(() -> new JavaScriptFrameworkNotFoundException(id));
        return mapper.mapEntityToResponse(repository.save(mapper.update(framework, request)));
    }

    @Override
    public JavaScriptFrameworkResponse create(CreateJavaScriptFrameworkRequest request) {
        if (repository.existsByName(request.getName())) {
            log.error("JavaScript framework with this name already exists. name: {}", request.getName());
            throw new JavaScriptFrameworkAlreadyExistsException(request.getName());
        }
        var framework = repository.save(mapper.mapToEntity(request));
        return mapper.mapEntityToResponse(framework);
    }

    @Override
    public boolean existsById(@NotNull Long id) {
        return repository.existsById(id);
    }

}
