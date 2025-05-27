package com.games.games_backend.entities;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stone {

    @Enumerated(EnumType.STRING)
    private PieceColor color;

    public Stone(PieceColor color) {
        this.color = color;
    }

    public String toString() {
        if (this.color == PieceColor.WHITE) {
            return "⚪";
        } else {
            return "⚫";
        }
    }
}