package reactor.seminar5.service;

import reactor.core.publisher.Flux;
import reactor.seminar5.model.Coordinates;

import java.time.Duration;
import java.util.Objects;

public class GeolocationService {
    private static volatile GeolocationService INSTANCE;
    private GeolocationService() {
    }

    public static synchronized GeolocationService getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new GeolocationService();
        }

        return INSTANCE;
    }

    public Flux<Coordinates> broadcast() {
        return Flux.create(fluxSink -> {
                    Coordinates coordinates = new Coordinates(59.926094, 30.317432);
                    fluxSink.next(coordinates);
                    for (double i = 1; i < 30 && !fluxSink.isCancelled(); i++) {
                        double delta = i / 200;
                        coordinates = new Coordinates(
                                coordinates.getLatitude() + delta,
                                coordinates.getLongitude() + delta);
                        fluxSink.next(coordinates);
                    }
                    fluxSink.complete();
                })
                .map(Coordinates.class::cast)
                .delayElements(Duration.ofSeconds(1))
                .share();
    }
}