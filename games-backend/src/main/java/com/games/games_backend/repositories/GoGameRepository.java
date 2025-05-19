package com.games.games_backend.repositories;

import com.games.games_backend.entities.GoGame;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoGameRepository extends CrudRepository<GoGame, Long> {
}
