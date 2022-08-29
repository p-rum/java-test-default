package com.etnetera.hr.data;

import com.etnetera.hr.enumeration.HypeLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple data entity describing basic properties of every JavaScript framework.
 *
 * @author Etnetera
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "java_script_framework", uniqueConstraints = @UniqueConstraint(name = "java_script_framework_name_uindex", columnNames = "name"))
public class JavaScriptFramework {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)

    private String name;

    @Enumerated(EnumType.STRING)
    private HypeLevel hypeLevel;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "java_script_framework_id")
    private List<JavaScriptFrameworkVersion> versions;


    @Override
    public String toString() {
        return "JavaScriptFramework [id=" + id + ", name=" + name + ", hypeLevel= " + hypeLevel.name() + ", versions= [" + versions.stream().map(JavaScriptFrameworkVersion::toString).collect(Collectors.joining(",")) + "]";
    }

}
