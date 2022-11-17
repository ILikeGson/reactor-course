package reactor.seminar5.repository;

import reactor.core.publisher.Flux;
import reactor.seminar5.model.*;

import java.util.Currency;
import java.util.Objects;

public class OrderDetailsRepository {
    private static volatile OrderDetailsRepository INSTANCE;

    private OrderDetailsRepository() {
    }

    public static synchronized OrderDetailsRepository getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new OrderDetailsRepository();
        }

        return INSTANCE;
    }

    public Flux<OrderDetails> findAll() {
        return Flux.just(
                OrderDetails.builder()
                        .id("O_1")
                        .userId("123")
                        .taxiId("№4")
                        .currency(Currency.getInstance("643"))
                        .price(305)
                        .tariffType(TariffType.ECONOM)
                        .taxiRoute(TaxiRoute.builder()
                                .from(new Coordinates(59.967759, 30.410271))
                                .to(new Coordinates(59.930954, 30.361046))
                                .distanceInMeters(4021)
                                .expectedTravelTimeInSeconds(1802)
                                .build())
                        .waitingTime(361)
                        .build(),
                OrderDetails.builder()
                        .id("O_2")
                        .userId("123")
                        .taxiId("№2")
                        .currency(Currency.getInstance("643"))
                        .price(10000)
                        .tariffType(TariffType.COMFORT_PLUS)
                        .taxiRoute(TaxiRoute.builder()
                                .from(new Coordinates(59.930954, 30.361046))
                                .to(new Coordinates(59.967759, 30.410271))
                                .distanceInMeters(4021)
                                .expectedTravelTimeInSeconds(378)
                                .build())
                        .waitingTime(12)
                        .build()

        );
    }
}