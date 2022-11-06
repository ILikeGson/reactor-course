package reactor.seminar4.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.seminar4.model.*;
import reactor.seminar4.repository.GameRepository;
import reactor.util.function.Tuples;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

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
        return gameRepository.findAll()
                .filter(game -> game.getWhite().username().equals(Names.MAGNUS_CARLSEN.getName())
                        || game.getBlack().username().equals(Names.MAGNUS_CARLSEN.getName()));
    }

    // 2. Вычислить среднюю продолжительность матча в блитц для всех игроков
    public Mono<Double> findBlitzMatchAverageDuration() {
        return gameRepository.findAll()
                .filter(game -> TimeControl.BLITZ_3_MIN.equals(game.getSettings().getTimeControl())
                        || TimeControl.BLITZ_5_MIN.equals(game.getSettings().getTimeControl()))
                .map(game -> game.getFinishedAt().toEpochSecond() - game.getStartedAt().toEpochSecond())
                .collect(Collectors.averagingDouble(sec -> sec));
    }

    // 3. Сгруппировать результаты игр определенного игрока
    public Flux<?> groupGameResultsForParticularPlayer(String username) {
        return gameRepository.findAll()
                .filter(game -> game.getWhite().username().equals(username)
                        || game.getBlack().username().equals(username))
                .groupBy(game -> {
                    if (game.getWhite().username().equals(username)) {
                        return game.getWhite().result();
                    }

                    return game.getBlack().result();
                })
                .flatMap(flux -> flux.collectList()
                        .map(games -> Tuples.of(flux.key(), games)));
    }

    // 4. Сгруппировать игры по типу побед
    public Flux<?> groupGamesByVictoryTypes() {
        return gameRepository.findAll()
                .groupBy(Game::getVictoryType)
                .flatMap(flux -> flux.collectList()
                        .map(games -> Tuples.of(flux.key(), games)));
    }

    // 5. Вычислить среднюю точность ходов для отдельного игрока
    public Mono<Double> findAverageMoveAccuracyForParticularPlayer(String username) {
        return gameRepository.findAll()
                .filter(game -> game.getWhite().username().equals(username)
                        || game.getBlack().username().equals(username))
                .map(Game::getMoves)
                .collect(Collectors.averagingInt(moves -> moves));
    }

    // 6. Найти игрока, который быстрее всех обыграл Магнуса Карлсена
    public Mono<String> findPlayerWhoHadTheFastestWinAgainstMagnusCarlsen() {
        return gameRepository.findAll()
                .filter(game -> {
                    if (game.getWhite().username().equals(Names.MAGNUS_CARLSEN.getName())) {
                        if (MatchResult.LOSE.equals(game.getWhite().result())) {
                            return false;
                        }
                    } else if (game.getBlack().username().equals(Names.MAGNUS_CARLSEN.getName())) {
                        if (MatchResult.LOSE.equals(game.getBlack().result())) {
                            return true;
                        }
                    }

                    return false;
                })
                .sort(Comparator.comparing(game ->
                        game.getFinishedAt().toEpochSecond() - game.getStartedAt().toEpochSecond()
                ))
                .next()
                .map(game -> {
                    if (game.getWhite().username().equals(Names.MAGNUS_CARLSEN.getName())) {
                        return game.getBlack().username();
                    }

                    return game.getWhite().username();
                });
    }
}
