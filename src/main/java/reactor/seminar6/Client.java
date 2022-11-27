package reactor.seminar6;

import reactor.core.publisher.Flux;
import reactor.seminar6.service.FootballService;
import reactor.utils.Subscribers;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        FootballService footballService = new FootballService();


        // Cold Publisher -> one to one
        // Hot Publisher -> one to many

        Flux<String> flux = footballService.broadcast()
                .cache();

        flux.subscribe(Subscribers.namedSubscriber("Lesha"));
        Thread.sleep(10000L);

        flux.subscribe(Subscribers.namedSubscriber("Igor"));
        Thread.sleep(10000L);
    }
}
