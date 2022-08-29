package com.etnetera.hr.repository;

import com.etnetera.hr.data.JavaScriptFrameworkVersion;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface JavaScriptFrameworkVersionRepository extends JpaRepository<JavaScriptFrameworkVersion, Long> {

    boolean existsByJavaScriptFrameworkIdAndVersion(@NotNull Long javascriptFrameworkId, @NotBlank String version);

    JavaScriptFrameworkVersion findByJavaScriptFrameworkIdAndVersion(@NotNull Long javascriptFrameworkId, @NotBlank String version);


    List<JavaScriptFrameworkVersion> findAllByJavaScriptFrameworkIdOrderByDeprecationDateDesc(@NotNull Long javascriptFrameworkId);
}
