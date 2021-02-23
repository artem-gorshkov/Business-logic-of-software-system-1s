package ru.itmo.blss.firstlab.data.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return deleted == comment.deleted &&
                id.equals(comment.id) &&
                Objects.equals(payload, comment.payload) &&
                Objects.equals(created, comment.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, payload, created, deleted);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", payload='" + payload + '\'' +
                ", created=" + created +
                ", deleted=" + deleted +
                '}';
    }
}