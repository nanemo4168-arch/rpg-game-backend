package com.javatpg.repository;

import com.javatpg.model.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {
    Optional<GameSession> findByPlayerId(Long playerId);
}
