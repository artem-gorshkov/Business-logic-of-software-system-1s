package ru.itmo.blss.report.data.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Complain {
    @Id
    @GeneratedValue
    private Integer id;
    private String payload;
    @ManyToOne
    private Comment comment;
    @ManyToOne
    private User author;
    @CreationTimestamp
    private LocalDateTime created;
}
