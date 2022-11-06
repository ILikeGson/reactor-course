package reactor.seminar4.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.seminar4.model.Game;
import reactor.seminar4.model.Names;
import reactor.seminar4.model.Player;
import reactor.seminar4.repository.PlayerRepository;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

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
        return playerRepository.findAll()
                .count();
    }

    // 2. Вычислить средний рейтинг игры в блитц у зарегистрированных игроков
    public Mono<Double> calculateAverageBlitzRating() {
        return playerRepository.findAll()
                .collect(Collectors.averagingInt(Player::getRatingBlitz));
    }

    // 3. Найти игрока, у которого наибольший рейтинг в классику по ФИДЕ
    public Mono<Player> findPlayerWithHighestClassicFideRating() {
        return playerRepository.findAll()
                .sort(Comparator.comparing(Player::getRatingClassicByFIDE))
                .last();
    }

    // 4. Получить отсортированный список по убыванию рейтинга игроков в пулю
    public Flux<Player> findPlayersSortedDescByBulletRating() {
        return playerRepository.findAll()
                .sort(Comparator.comparing(Player::getRatingBullet).reversed());
    }

    // 5. Вывести на экран всех игроков, кто сейчас онлайн
    public Flux<Player> findOnlinePlayers() {
        return playerRepository.findAll()
                .filter(Player::isOnline);
    }

    // 6. Найти игрока, который зарегистрировался раньше всех на chess.com и имеет наибольший рейтинг игры в рапид
    public Mono<Player> findPlayerWithEarliestRegistrationDate() {
        return playerRepository.findAll()
                .sort(Comparator.comparing(Player::getRegisteredAt)
                        .thenComparing(Comparator.comparingInt(Player::getRatingRapid).reversed()))
                .next();
    }

    // 7. Найти игроков, у которых в друзьях есть Daniel Naroditsky
    public Flux<Player> findPlayersHavingInFriendsDanielNaroditsky() {
        return playerRepository.findAll()
                .filter(player -> {
                    for (Player friend : player.getFriends()) {
                        if (Names.DANIEL_NARODITSKY.getName().equals(friend.getUsername())) {
                            return true;
                        }
                    }

                    return false;
                });
    }

    // 8. Вывести инфу об игроке и 10ти его последних играх
    public Flux<Player> findPlayerWith10LastGames(String playerUsername) {
        return playerRepository.findAll()
                .filter(player -> player.getUsername().equals(playerUsername))
                .zipWith(gameService.findAll()
                                .filter(game -> game.getWhite().username().equals(playerUsername)
                                        || game.getBlack().username().equals(playerUsername))
                                .sort(Comparator.comparing(Game::getStartedAt).reversed())
                                .take(10)
                                .collectList(), (player, games) -> {
                            player.setGames(games);
                            return player;
                        });
    }
}
