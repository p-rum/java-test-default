package com.etnetera.hr.data;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;


@Entity
@Table(name = "java_script_framework_version",
        uniqueConstraints = @UniqueConstraint(name = "unique_js_fw_version", columnNames = {"java_script_framework_id", "version"}))
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class JavaScriptFrameworkVersion {


    @Column(name = "deprecation_date")
    private Instant deprecationDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "java_script_framework_id")
    private Long javaScriptFrameworkId;

    @Column(name = "version", nullable = false)
    private String version;


    @Override
    public String toString() {
        return "JavaScriptFrameworkVersion[" +
                "deprecationDate=" + this.deprecationDate +
                ", version= " + getVersion() +
                ']';
    }
}
