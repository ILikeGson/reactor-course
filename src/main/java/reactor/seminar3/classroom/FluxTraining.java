package reactor.seminar3.classroom;

import reactor.core.publisher.Flux;
import reactor.seminar3.classroom.model.Character;
import reactor.utils.Generator;
import reactor.utils.Logger;

import java.time.Instant;

public class FluxTraining {
    public static void main(String[] args) {
        // fluxCreate();
        fluxGenerate();
    }

    public static void fluxCreate() {
        Flux.create(fluxSink -> {
                    Logger.log("inside");
                    int prev = 0;
                    int current = 1;
                    fluxSink.next(prev);
                    fluxSink.next(current);

                    while (!fluxSink.isCancelled()) {
                        int inter = prev + current;
                        prev = current;
                        current = inter;
                        fluxSink.next(current);
                    }

                    fluxSink.complete();
                })
                .take(20)
                .subscribe(Logger::log,
                        throwable -> Logger.log(throwable.getMessage()),
                        () -> Logger.log("Completed"),
                        subscription -> subscription.request(Long.MAX_VALUE));
    }

    public static void fluxGenerate() {
        Flux.generate(sink ->
                    sink.next(new Character(
                            Generator.faker().harryPotter().character(),
                            Generator.faker().harryPotter().spell()))
                )
                .index()
                .timestamp()
                .take(20)
                .map(tuple -> "timestamp: %s, index: %s, character: %s".formatted(
                        Instant.ofEpochMilli(tuple.getT1()),
                        tuple.getT2().getT1(),
                        tuple.getT2().getT2()))
                .subscribe(Logger::log,
                        Logger::log,
                        () -> Logger.log("Completed"),
                        subscription -> subscription.request(Long.MAX_VALUE));
    }
}
