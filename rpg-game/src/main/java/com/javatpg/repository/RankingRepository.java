package com.javatpg.repository;

import com.javatpg.model.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, Long> {
    List<Ranking> findAllByOrderByScoreDesc(Pageable pageable);
    List<Ranking> findByNickname(String nickname);
}
