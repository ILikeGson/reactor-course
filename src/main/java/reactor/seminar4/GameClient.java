package reactor.seminar4;

import reactor.seminar4.model.Names;
import reactor.seminar4.service.GameService;
import reactor.utils.Subscribers;

public class GameClient {
    public static void main(String[] args) {
        GameService gameService = GameService.getInstance();

        gameService.findAllMagnusCarlsenGames()
                .subscribe();

        gameService.findBlitzMatchAverageDuration()
                        .subscribe();

        gameService.groupGameResultsForParticularPlayer(Names.MAGNUS_CARLSEN.getName())
                .subscribe();

        gameService.groupGamesByVictoryTypes()
                .subscribe();

        gameService.findPlayerWhoHadTheFastestWinAgainstMagnusCarlsen()
                .subscribe(Subscribers.defaultSubscriber());
    }
}
