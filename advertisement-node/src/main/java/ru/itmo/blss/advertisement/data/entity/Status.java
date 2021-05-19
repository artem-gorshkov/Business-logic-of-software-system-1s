package ru.itmo.blss.advertisement.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Status {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
}
