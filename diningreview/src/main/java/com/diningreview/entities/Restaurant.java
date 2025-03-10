package com.diningreview.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Entity
@Table(name="restaurants")
@AllArgsConstructor
@ToString
public class Restaurant {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Setter
    @Column(name="overall_score")
    private Long overallScore;

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
