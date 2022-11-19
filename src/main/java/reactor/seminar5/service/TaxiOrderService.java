package reactor.seminar5.service;

import org.apache.commons.lang3.RandomUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.seminar5.model.*;
import reactor.seminar5.repository.TaxiInfoRepository;
import reactor.utils.Logger;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class TaxiOrderService {
    private static volatile TaxiOrderService INSTANCE;
    private final GeolocationService geolocationService;
    private final TaxiInfoRepository taxiInfoRepository;
    private final FareEstimationService fareEstimationService;
    private final TrafficEstimationService trafficEstimationService;
    private final DistanceEstimationService distanceEstimationService;
    private final TravelTimeEstimationService travelTimeEstimationService;

    private TaxiOrderService() {
        geolocationService = GeolocationService.getInstance();
        taxiInfoRepository = TaxiInfoRepository.getInstance();
        fareEstimationService = FareEstimationService.getInstance();
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

    public Flux<TaxiInfo> findThreeNearestTaxisByCoordinates(Coordinates clientCoords) {
        return taxiInfoRepository.findAll()
                .sort(Comparator.comparing(taxi -> distanceEstimationService.estimateDistanceInStraightLineInMeters(
                        clientCoords, taxi.getLastGpsLocation())))
                .take(3);
    }

    public Mono<TaxiInfo> suggestOrderToNearestTaxis(Coordinates clientCoords) {
        return findThreeNearestTaxisByCoordinates(clientCoords)
                .filter(taxi -> RandomUtils.nextInt(0, 2) == 1)
                .next();
    }

    public Mono<TaxiInfo> findNearestWorkingTaxiWithEconomTariff(Coordinates clientCoords) {
        return taxiInfoRepository.findAll()
                .filter(taxi -> taxi.isWorking() && TariffType.ECONOM.equals(taxi.getTariffType()))
                .sort(Comparator.comparing(taxi -> distanceEstimationService.estimateDistanceInStraightLineInMeters(
                        clientCoords, taxi.getLastGpsLocation())))
                .next();
    }

    // Получить данные движения такси. Замерить время между их получением.
    public Flux<?> trackTaxiLocation() {
        return geolocationService.broadcast()
                .elapsed();
    }

    // Отследить среднюю скорость такси. Обрабатывать данные каждые 5 секунд.
    public Flux<?> trackAverageTaxiSpeed() {
        return geolocationService.broadcast()
                .window(Duration.ofSeconds(5))
                .flatMap(Flux::collectList)
                .filter(list -> list.size() > 1)
                .mapNotNull(list -> {
                    List<Double> distances = new Vector<>();
                    for (int i = 1; i < list.size(); i++) {
                        Coordinates start = list.get(i - 1);
                        Coordinates end = list.get(i);
                        distances.add(distanceEstimationService.estimateDistanceInStraightLineInMeters(start, end));
                    }

                    return distances.stream()
                            .reduce(Double::sum)
                            .map(sum -> sum / (list.size() - 1))
                            .orElse(0.0);
                })
                .doOnNext(avgSpeed -> Logger.log("Avg speed: " + avgSpeed));
    }

    public Mono<OrderDetails> createOrder(Request request) {
        return Mono.fromSupplier(() -> createOrderDetails(request))
                .zipWith(taxiInfoRepository.findAll()
                        .filter(taxi -> taxi.isWorking() && TariffType.ECONOM.equals(taxi.getTariffType()))
                        .sort(Comparator.comparing(taxi -> distanceEstimationService.estimateDistanceInStraightLineInMeters(
                                request.getFrom(), taxi.getLastGpsLocation())))
                        .collectList(), (orderDetails, taxiList) -> {
                    taxiList.stream()
                            .filter(taxi -> RandomUtils.nextInt(0, 2) == 1)
                            .findFirst()
                            .map(taxiInfo -> {
                                double distance = distanceEstimationService.estimateDistanceInStraightLineInMeters(
                                        orderDetails.getTaxiRoute().getFrom(),
                                        taxiInfo.getLastGpsLocation());
                                TrafficJamIndex trafficJamIndex = trafficEstimationService.estimateCurrentTraffic();
                                double waitingTime = travelTimeEstimationService.estimateTravelTimeInSecondsConsideringTraffic(
                                        distance, trafficJamIndex);
                                orderDetails.setTaxiId(taxiInfo.getTaxiId());
                                orderDetails.setWaitingTime(waitingTime);
                                return taxiInfo;
                            });
                    return orderDetails;
                });
    }

    private OrderDetails createOrderDetails(Request request) {
        TrafficJamIndex trafficJamIndex = trafficEstimationService.estimateCurrentTraffic();
        double distance = distanceEstimationService.estimateDistanceInStraightLineInMeters(request.getFrom(), request.getTo());
        double travelTime = travelTimeEstimationService.estimateTravelTimeInSecondsConsideringTraffic(distance, trafficJamIndex);

        return OrderDetails.builder()
                .id("Order-1")
                .userId(request.getUserId())
                .tariffType(request.getTariffType())
                .taxiRoute(TaxiRoute.builder()
                        .to(request.getTo())
                        .from(request.getFrom())
                        .distanceInMeters(distance)
                        .expectedTravelTimeInSeconds(travelTime)
                        .build())
                .price(fareEstimationService.estimateFare(travelTime, request.getTariffType()))
                .currency(request.getCurrency())
                .build();
    }
}