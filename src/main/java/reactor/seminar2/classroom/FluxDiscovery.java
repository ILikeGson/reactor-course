package reactor.seminar2.classroom;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.utils.DefaultSubscriber;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FluxDiscovery {
    // flux from mono
    // mono from flux

    // null's are forbidden to emit in Reactive Streams spec
    // publisher may have many subscribers

    // flux wrappers
    // Spring Web MVC & Spring WebFlux

    public static void main(String[] args) {
        /*

        Mono<Integer> mono = Mono.just(1);
        Flux<Integer> from = Flux.from(mono);

        Flux<String> flux = Flux.just("1", "2", "3");
        Mono.from(flux);

        */


        /*
        Flux<String> flux = Flux.just(null, "2", "3");

        flux.subscribe(DefaultSubscriber.subscriber());
        flux.subscribe(DefaultSubscriber.subscriber());
        */

        /*Flux.just("Vlad", "Nikita", "Vasilii")
                .map(str -> str.toUpperCase())
                .filter(str -> true)
                .subscribe(DefaultSubscriber.subscriber());

        Flux<String> flux = Flux.just("Vlad", "Nikita", "Vasilii");
        Flux<String> fluxMap = new FluxMap(flux, str -> str.toUpperCase());
        Flux<String> fluxFilter = new FluxFilter(fluxMap, str -> true);

        new FluxFilter(new FluxMap(Flux.just(), mapping), predicate);
        new BufferedReader(new InputStreamReader(new FileInputStream("")));*/
    }
}
