package com.diningreview.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="dining_reviews")
@AllArgsConstructor
@Getter
@ToString
public class DiningReview {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Setter
    @Column(name="username")
    private String username;
    @Setter
    @Column(name="restaurant_id")
    private Long restaurantId;
    @Setter
    @Column(name="peanut_score")
    private Double peanutScore;
    @Setter
    @Column(name="egg_score")
    private Double eggScore;
    @Setter
    @Column(name="dairy_score")
    private Double dairyScore;
    @Setter
    @Column(name="commentary")
    private String commentary;

    @Setter
    @Column(name="status")
    private DiningReviewStatus status;
}
