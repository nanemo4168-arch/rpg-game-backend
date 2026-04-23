package com.javatpg.controller;

import com.javatpg.dto.GameDto.*;
import com.javatpg.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/start")
    public ResponseEntity<StartResponse> startGame(@RequestBody StartRequest request) {
        return ResponseEntity.ok(gameService.startGame(request));
    }

    @GetMapping("/question")
    public ResponseEntity<QuestionResponse> getQuestion(@RequestParam Long playerId) {
        return ResponseEntity.ok(gameService.getQuestion(playerId));
    }

    @PostMapping("/answer")
    public ResponseEntity<AnswerResponse> submitAnswer(@RequestBody AnswerRequest request) {
        return ResponseEntity.ok(gameService.processAnswer(request));
    }

    @GetMapping("/state")
    public ResponseEntity<GameStateResponse> getGameState(@RequestParam Long playerId) {
        return ResponseEntity.ok(gameService.getGameState(playerId));
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<RankingEntry>> getRanking() {
        return ResponseEntity.ok(gameService.getRankings());
    }
}
