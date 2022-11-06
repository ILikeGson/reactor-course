package reactor.seminar4.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.seminar4.model.Player;
import reactor.seminar4.repository.PlayerRepository;

import java.util.Objects;

public class PlayerService {
    private static volatile PlayerService INSTANCE;

    private final GameService gameService;
    private final PlayerRepository playerRepository;

    private PlayerService() {
        gameService = GameService.getInstance();
        playerRepository = PlayerRepository.getInstance();
    }

    public static synchronized PlayerService getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new PlayerService();
        }

        return INSTANCE;
    }

    // 1. Вычислить количество зарегистрированных игроков
    public Mono<Long> countRegisteredUsers() {
        return Mono.empty();
    }

    // 2. Вычислить средний рейтинг игры в блитц у зарегистрированных игроков
    public Mono<Double> calculateAverageBlitzRating() {
        return Mono.empty();
    }

    // 3. Найти игрока, у которого наибольший рейтинг в классику по ФИДЕ
    public Mono<Player> findPlayerWithHighestClassicFideRating() {
        return Mono.empty();
    }

    // 4. Получить отсортированный список по убыванию рейтинга игроков в пулю
    public Flux<Player> findPlayersSortedDescByBulletRating() {
        return Flux.empty();
    }

    // 5. Вывести на экран всех игроков, кто сейчас онлайн
    public Flux<Player> findOnlinePlayers() {
        return Flux.empty();
    }

    // 6. Найти игрока, который зарегистрировался раньше всех на chess.com и имеет наибольший рейтинг игры в рапид
    public Mono<Player> findPlayerWithEarliestRegistrationDate() {
        return Mono.empty();
    }

    // 7. Найти игроков, у которых в друзьях есть Daniel Naroditsky
    public Flux<Player> findPlayersHavingInFriendsDanielNaroditsky() {
        return Flux.empty();
    }

    // 8. Вывести инфу об игроке и 10ти его последних играх
    public Flux<Player> findPlayerWith10LastGames(String playerUsername) {
        return Flux.empty();
    }
}
