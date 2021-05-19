package ru.itmo.blss.main.data.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Post {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String payload;
    @ManyToOne
    private User author;
    @ManyToOne
    private Topic topic;
    @CreationTimestamp
    private LocalDateTime created;
    @OneToMany
    @JoinColumn(name = "post_id")
    private Set<Comment> comments;
    private boolean isPaid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.id) &&
                Objects.equals(title, post.title) &&
                Objects.equals(payload, post.payload) &&
                Objects.equals(created, post.created) &&
                Objects.equals(isPaid, post.isPaid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, payload, created, isPaid);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", payload='" + payload + '\'' +
                ", author=" + author +
                ", created=" + created +
                ", isPaid=" + isPaid +
                '}';
    }
}
