package com.games.games_backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

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

    @Column(name="board_dimension")
    private int boardDimension;

    @Column(name="moves_log")
    private String movesLog;

    @Column(name="komi")
    private double komi;

    @Column(name="black_captures")
    private int blackCaptures;

    @Column(name="white_captures")
    private int whiteCaptures;

    @Column(name ="created_timestamp")
    private Date createdTimestamp;
}
