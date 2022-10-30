package reactor.seminar2.classroom;

import lombok.SneakyThrows;
import reactor.core.publisher.Flux;
import reactor.seminar2.classroom.gateway.GithubClient;
import reactor.seminar2.classroom.gateway.OfficialJokeClient;
import reactor.utils.DefaultSubscriber;
import reactor.utils.Generator;
import reactor.utils.Logger;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FluxTraining {
    public static void main(String[] args) throws InterruptedException {
        // createFluxEmpty();
        // createFluxNever();
        // createFluxJust();
        // createFluxRange();
        // createFluxFromArray();
        // createFluxFromIterable();
        // createFluxFromStream();
        // createFluxInterval();

        /*GithubClient githubClient = new GithubClient();

        githubClient.findCommitsInMasterByProjectName("reactor-course")
                .index()
                .timestamp()
                .subscribe(DefaultSubscriber.subscriber());*/

        OfficialJokeClient jokeClient = new OfficialJokeClient();

        Flux.fromStream(jokeClient::getJokes)
                        .subscribe(DefaultSubscriber.subscriber());
    }

    private static void createFluxEmpty() {
        Flux.empty()
                .subscribe(
                        Logger::log,
                        throwable -> Logger.log(throwable.getMessage()),
                        () -> Logger.log("Completed"),
                        subscription -> subscription.request(Long.MAX_VALUE));
    }

    private static void createFluxNever() {
        Flux.never()
                .subscribe(
                        Logger::log,
                        throwable -> Logger.log(throwable.getMessage()),
                        () -> Logger.log("Completed"),
                        subscription -> subscription.request(Long.MAX_VALUE));
    }

    private static void createFluxJust() {
        Flux.just("Vlad", "Nikita", "Vasilii")
                .subscribe(
                        Logger::log,
                        throwable -> Logger.log(throwable.getMessage()),
                        () -> Logger.log("Completed"),
                        subscription -> subscription.request(Long.MAX_VALUE));
    }

    private static void createFluxRange() {
        Flux.range(1, 100)
                .map(i -> Generator.generateUser())
                .subscribe(DefaultSubscriber.subscriber());
    }

    private static void createFluxFromArray() {
        Flux.fromArray(new String[]{"F", "D", "A"})
                .subscribe(DefaultSubscriber.subscriber());
    }

    private static void createFluxFromIterable() {
        Flux.fromIterable(List.of(
                        Generator.generateFilmName(),
                        Generator.generateFilmName(),
                        Generator.generateFilmName()))
                .subscribe(DefaultSubscriber.subscriber());
    }

    private static void createFluxFromStream() {
        Stream<String> stream = IntStream.rangeClosed(1, 1000)
                .mapToObj(String::valueOf);


        Flux.fromStream(() -> stream)
                .map(str -> Thread.currentThread().getName())
                .index()
                .subscribe(DefaultSubscriber.subscriber());
    }

    @SneakyThrows
    private static void createFluxInterval() {
        Flux.interval(Duration.ofSeconds(1))
                .subscribe(DefaultSubscriber.subscriber());
    }


}
