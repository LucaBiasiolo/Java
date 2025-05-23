package com.games.games_backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name="go_board")
public class GoBoard {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="board_dimension")
    private int boardDimension;

    @OneToOne
    @JoinColumn(name = "game_id")
    private GoGame game;

    @Column(name="board_csv")
    private String boardCsv;
}
