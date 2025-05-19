package com.games.games_backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="go_games")
@Getter
@Setter
@ToString
public class GoGame {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;


}
