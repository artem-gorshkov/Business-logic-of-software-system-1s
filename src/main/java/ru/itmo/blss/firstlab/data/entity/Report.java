package ru.itmo.blss.firstlab.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class Report {
    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne
    private Comment comment;
    @OneToOne
    private Status status;
}
