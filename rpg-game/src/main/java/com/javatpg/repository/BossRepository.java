package com.javatpg.repository;

import com.javatpg.model.Boss;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BossRepository extends JpaRepository<Boss, Long> {
    Optional<Boss> findByStage(int stage);
}
