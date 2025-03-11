package com.diningreview.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name="restaurants")
@ToString
public class Restaurant {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="zipcode")
    private String zipCode;

    @Setter
    @Column(name="overall_score")
    private Double overallScore;

    @Setter
    @Column(name="peanuts_score")
    private Double peanutsScore;

    @Setter
    @Column(name="egg_score")
    private Double eggScore;

    @Setter
    @Column(name="dairy_score")
    private Double dairyScore;
}
