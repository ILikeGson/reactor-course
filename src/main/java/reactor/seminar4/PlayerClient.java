package reactor.seminar4;

import reactor.seminar4.model.Names;
import reactor.seminar4.service.PlayerService;
import reactor.utils.Subscribers;

public class PlayerClient {

    public static void main(String[] args) throws Exception {
        playerServiceClient();
    }

    private static void playerServiceClient() {
        PlayerService playerService = PlayerService.getInstance();

        // 1. Вычислить количество зарегистрированных игроков
        playerService.countRegisteredUsers()
                .subscribe();

        // 2. Вычислить средний рейтинг игры в блитц у зарегистрированных игроков
        playerService.calculateAverageBlitzRating()
                .subscribe();

        // 3. Найти игрока, у которого наибольший рейтинг в классику по ФИДЕ
        playerService.findPlayerWithHighestClassicFideRating()
                .subscribe();

        // 4. Получить отсортированный список по убыванию рейтинга игроков в пулю
        playerService.findPlayersSortedDescByBulletRating()
                .subscribe();

        // 5. Вывести на экран всех игроков, кто сейчас онлайн
        playerService.findOnlinePlayers().elapsed()
                .subscribe();

        // 6. Найти игрока, который зарегистрировался раньше всех на chess.com и имеет наибольший рейтинг игры в рапид
        playerService.findPlayerWithEarliestRegistrationDate()
                .subscribe();

        // 7. Найти игроков, у которых в друзьях есть Daniel Naroditsky
        playerService.findPlayersHavingInFriendsDanielNaroditsky()
                .subscribe(Subscribers.defaultSubscriber());

        // 8. Вывести инфу об игроке и 10ти его последних играх
        playerService.findPlayerWith10LastGames(Names.HIKARU.getName())
                .subscribe(Subscribers.defaultSubscriber());
    }
}
