package com.games.games_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="go_move")
@Getter
@Setter
public class Move {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="matrix_row")
    private int row;

    @Column(name="matrix_column")
    private int column;

    @Column(name="color")
    private String color;

    @Column(name="is_pass")
    private boolean isPass;

    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonBackReference
    private GoGame game;
}
