package com.diningreview.entities;


/*A user consists of the following info:

their display name, one that’s unique to only that user
city
state
zipcode
whether they’re interested in peanut allergies
whether they’re interested in egg allergies
whether they’re interested in dairy allergies*/

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="users")
@ToString
@Getter
public class User {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="username", unique = true, nullable = false)
    @Setter
    private String username;

    @Column(name="password", nullable = false, unique = true)
    @Setter
    private String password;

    @Column(name="city")
    @Setter
    private String city;

    @Column(name="state")
    @Setter
    private String state;

    @Column(name="zipcode")
    @Setter
    private String zipCode;

    @Column(name="interest_peanut_allergy")
    @Setter
    private Boolean interestPeanutAllergy;

    @Column(name="interest_egg_allergy")
    @Setter
    private Boolean interestEggAllergy;

    @Column(name="interest_dairy_allergy")
    @Setter
    private Boolean interestDairyAllergy;

    @Column(name="is_admin", nullable = false, columnDefinition = "boolean default false")
    @Setter
    private Boolean isAdmin = false;
}
