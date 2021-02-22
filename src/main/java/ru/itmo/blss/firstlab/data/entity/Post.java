package ru.itmo.blss.firstlab.data.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String payload;
    @ManyToOne
    private Users author;
    @CreationTimestamp
    private LocalDateTime created;
    @OneToMany
    @JoinColumn(name = "post")
    private Set<Comment> comments;
}
