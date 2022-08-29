package com.etnetera.hr.mapper;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.data.JavaScriptFrameworkVersion;
import com.etnetera.hr.model.request.CreateJavaScriptFrameworkRequest;
import com.etnetera.hr.model.request.CreateJavaScriptFrameworkVersionRequest;
import com.etnetera.hr.model.request.UpdateJavaScriptFrameworkRequest;
import com.etnetera.hr.model.request.UpdateJavaScriptFrameworkVersionRequest;
import com.etnetera.hr.model.response.JavaScriptFrameworkResponse;
import com.etnetera.hr.model.response.JavaScriptFrameworkVersionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JavaScriptFrameworkMapper {

    JavaScriptFrameworkResponse mapEntityToResponse(JavaScriptFramework entity);

    List<JavaScriptFrameworkResponse> mapListToResponse(List<JavaScriptFramework> list);


    @Mapping(target = "versions", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "id", ignore = true)
    JavaScriptFramework update(@MappingTarget JavaScriptFramework entity, UpdateJavaScriptFrameworkRequest request);

    @Mapping(target = "javaScriptFrameworkId", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    JavaScriptFrameworkVersion updateVersion(@MappingTarget JavaScriptFrameworkVersion entity, UpdateJavaScriptFrameworkVersionRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "javaScriptFrameworkId", source = "javaScriptFrameworkId")
    JavaScriptFrameworkVersion mapVersionRequestToEntity(CreateJavaScriptFrameworkVersionRequest request, Long javaScriptFrameworkId);

    @Mapping(target = "versions", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "hypeLevel", source = "hypeLevel")
    JavaScriptFramework mapToEntity(CreateJavaScriptFrameworkRequest request);


    JavaScriptFrameworkVersionResponse mapVersionToResponse(JavaScriptFrameworkVersion version);


}
