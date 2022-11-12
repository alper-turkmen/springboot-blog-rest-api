package com.springboot.ecommerceproject.entity;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//JPA is smart enough to name table posts if we dont specify
@Table(name = "products", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;
    //JPA is smart enought to name the column name as the field name

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;

    //Set can contain one item one time
    //orphanremove means whenever we remove parent it removes
    //cascadetype.ALL means propagates all operations from a parent to a child entity
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();
}
