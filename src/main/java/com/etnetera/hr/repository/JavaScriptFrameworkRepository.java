package com.etnetera.hr.repository;

import com.etnetera.hr.data.JavaScriptFramework;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring data repository interface used for accessing the data in database.
 *
 * @author Etnetera
 */
@Repository
public interface JavaScriptFrameworkRepository extends CrudRepository<JavaScriptFramework, Long> {

    List<JavaScriptFramework> findAll();

    JavaScriptFramework findByName(String name);

    Boolean existsByName(String name);


}