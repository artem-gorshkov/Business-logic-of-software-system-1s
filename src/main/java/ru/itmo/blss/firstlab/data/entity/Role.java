package ru.itmo.blss.firstlab.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    /*@ManyToMany(mappedBy = "roles")
    private Set<User> users;*/

}
