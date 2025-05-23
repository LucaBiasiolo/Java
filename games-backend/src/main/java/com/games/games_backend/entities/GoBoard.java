package com.games.games_backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="go_board")
@Getter
@Setter
public class GoBoard {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="board_dimension")
    private Integer boardDimension;

    @Column(name="board_csv")
    private String boardCsv;
}
