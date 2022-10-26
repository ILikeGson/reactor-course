package reactor.seminar1.classroom;

import lombok.extern.java.Log;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Mono;
import reactor.seminar1.classroom.service.JasonStathamQuotesService;
import reactor.seminar1.classroom.service.WeatherForecastService;
import reactor.utils.Generator;
import reactor.utils.Logger;
import reactor.validation.RequestValidator;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class MonoTraining {
    public static void main(String[] args) {
        /*Mono<Integer> mono = Mono.just(1);
        Mono<String> stringMono = Mono.just("mono");

        Mono<Object> objectMono = Mono.justOrEmpty(Optional.empty());
        // Mono.justOrEmpty(object) = (object == null ? Mono.empty() : Mono.just(object) )

        Mono<Object> completed = Mono.fromRunnable(() -> System.out.println("Completed"));
        Mono<String> stringMono1 = Mono.fromSupplier(Generator::generateFilmName);

        Mono.fromCallable(Generator::generateFilmName)
                .subscribe(System.out::println);
*/
        /*String token = "I_LOVE_JASON_STATHAM_123456";
        JasonStathamQuotesService quotesService = new JasonStathamQuotesService();

        Mono.defer(() -> RequestValidator.checkUserToken(token)
                        ? Mono.fromCallable(quotesService::getFamousQuote)
                        : Mono.error(() -> new RuntimeException("Token is invalid")))
                .subscribe(
                        str -> Logger.log("OnNext"),
                        throwable -> Logger.log("OnError"),
                        () -> Logger.log("OnComplete"),
                        subscription -> {
                            Logger.log("OnSubscribe");
                            subscription.request(1);
                        });*/

        /*Mono.just(1)
                .subscribe(Logger::log);*/

        WeatherForecastService forecastService = new WeatherForecastService();

        Mono.fromCallable(forecastService::getWeatherForecastInSofia)
                .map(String.class::cast)
                .map(str -> str)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(1);
                    }

                    @Override
                    public void onNext(String s) {
                        Logger.log(s);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {
                        Logger.log("Completed");
                    }
                });
    }
}