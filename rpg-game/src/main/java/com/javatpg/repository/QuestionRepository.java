package com.javatpg.repository;

import com.javatpg.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT * FROM questions WHERE stage = :stage AND id NOT IN (:excludeIds) ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Question findRandomByStageExcluding(@Param("stage") int stage, @Param("excludeIds") List<Long> excludeIds);

    @Query(value = "SELECT * FROM questions WHERE stage = :stage ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Question findRandomByStage(@Param("stage") int stage);
}
