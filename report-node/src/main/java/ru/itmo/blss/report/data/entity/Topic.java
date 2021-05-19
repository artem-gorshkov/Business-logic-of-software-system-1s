package ru.itmo.blss.report.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Topic {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;

    public Topic(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
