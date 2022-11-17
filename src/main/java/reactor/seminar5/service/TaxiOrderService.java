package reactor.seminar5.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.seminar5.model.TaxiInfo;
import reactor.seminar5.repository.TaxiInfoRepository;
import reactor.seminar5.repository.TrafficEstimationService;

import java.util.Objects;

public class TaxiOrderService {
    private static volatile TaxiOrderService INSTANCE;
    private final GeolocationService geolocationService;
    private final TaxiInfoRepository taxiInfoRepository;
    private final TrafficEstimationService trafficEstimationService;
    private final DistanceEstimationService distanceEstimationService;
    private final TravelTimeEstimationService travelTimeEstimationService;

    private TaxiOrderService() {
        geolocationService = GeolocationService.getInstance();
        taxiInfoRepository = TaxiInfoRepository.getInstance();
        trafficEstimationService = TrafficEstimationService.getInstance();
        distanceEstimationService = DistanceEstimationService.getInstance();
        travelTimeEstimationService = TravelTimeEstimationService.getInstance();
    }

    public static synchronized TaxiOrderService getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new TaxiOrderService();
        }

        return INSTANCE;
    }

    public static Flux<TaxiInfo> findThreeNearestTaxisByCoordinates() {
        return Flux.empty();
    }

    public static Mono<TaxiInfo> suggestOrderToNearestTaxis() {
        return Mono.empty();
    }

    public static Mono<TaxiInfo> findNearestWorkingTaxiWithEconomTariff() {
        return Mono.empty();
    }

    // Получить данные движения такси. Замерить время между их получением.
    public static Flux<?> trackTaxiLocation() {
        return Flux.empty();
    }

    // Отследить среднюю скорость такси. Обрабатывать данные каждые 5 секунд.
    public static Flux<?> trackAverageTaxiSpeed() {
        return Flux.empty();
    }
}