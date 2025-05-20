package com.games.games_backend.entities;

import lombok.Getter;

@Getter
public enum PieceColor {
    BLACK("Black"),WHITE("White");

    private String description;

    PieceColor(String description) {
        this.description = description;
    }
}
