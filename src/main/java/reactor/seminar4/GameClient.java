package reactor.seminar4;

import reactor.seminar4.model.Names;
import reactor.seminar4.service.GameService;

public class GameClient {
    public static void main(String[] args) {
        GameService gameService = GameService.getInstance();

        gameService.findBlitzMatchAverageDuration()
                .subscribe();

        gameService.groupGameResultsForParticularPlayer(Names.MAGNUS_CARLSEN.getName())
                .subscribe();
    }
}
