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
@Table(name = "pets")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "turu", nullable = false)
    private String turu;

    @Column(name = "cinsi", nullable = false)
    private String cinsi;

    @Column(name = "dogum", nullable = false)
    private String dogum;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "childs", nullable = false)
    private String childs;


    @Column(name = "irki", nullable = false)
    private String irki;

    @Column(name = "vet", nullable = false)
    private String vet;

    @Column(name = "hastalik", nullable = false)
    private String hastalik;

    @Column(name = "geo", nullable = false)
    private String geo;

    @Column(name = "latitude", nullable = false)
    private String latitude;


    @Column(name = "longitude", nullable = false)
    private String longitude;

    @Column(name = "lastlocationdate", nullable = false)
    private String lastlocationdate;



}
