package ru.itmo.blss.firstlab.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
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
    private User author;
    @CreationTimestamp
    private LocalDateTime created;
    @OneToMany
    @JoinColumn(name = "post")
    private Set<Comment> comments;
}
