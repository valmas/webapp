package com.spboot.webapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    private long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "AGE")
    private int age;
    @ManyToMany
    private Set<Person> friends = new HashSet<>();
}
