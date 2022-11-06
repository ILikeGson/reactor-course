package reactor.seminar4.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.seminar4.model.Game;
import reactor.seminar4.model.Player;
import reactor.seminar4.repository.GameRepository;

import java.util.Objects;

public class GameService {
    private static volatile GameService INSTANCE;

    private final GameRepository gameRepository;

    private GameService() {
        this.gameRepository = GameRepository.getInstance();
    }

    public static synchronized GameService getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new GameService();
        }

        return INSTANCE;
    }

    public Flux<Game> findAll() {
        return gameRepository.findAll();
    }

    // 1. Вывести на экран все игры Magnus Carlsen
    public Flux<Game> findAllMagnusCarlsenGames() {
        return Flux.empty();
    }

    // 2. Вычислить среднюю продолжительность матча в блитц для всех игроков
    public Mono<Double> findBlitzMatchAverageDuration() {
        return Mono.empty();
    }

    // 3. Сгруппировать результаты игр определенного игрока
    public Flux<?> groupGameResultsForParticularPlayer(String username) {
        return Flux.empty();
    }

    // 4. Сгруппировать игры по типу побед
    public Flux<?> groupGamesByVictoryTypes() {
        return Flux.empty();
    }

    // 5. Вычислить среднюю точность ходов для отдельного игрока
    public Mono<Long> findAverageMoveAccuracyForParticularPlayer() {
        return Mono.empty();
    }

    // 6. Найти игрока, который быстрее всех обыграл Магнуса Карлсена
    public Mono<Player> findPlayerWhoHadTheFastestWinAgainstMagnusCarlsen() {
        return Mono.empty();
    }
}
