package com.games.games_backend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="go_game")
@Getter
@Setter
@ToString
public class GoGame {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="moves")
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Move> moves = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="go_board_id")
    private GoBoard goBoard;

    @Column(name="komi")
    private double komi;

    @Column(name="black_captures")
    private int blackCaptures;

    @Column(name="white_captures")
    private int whiteCaptures;

    @Column(name ="created_at")
    private Date createdAt;
}
