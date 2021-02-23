package ru.itmo.blss.firstlab.data.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Integer id;
    private String payload;
    @ManyToOne
    private Post post;
    @ManyToOne
    private User author;
    @CreationTimestamp
    private LocalDateTime created;
    private boolean deleted;
}