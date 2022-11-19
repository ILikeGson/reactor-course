package reactor.seminar5.service;

import reactor.seminar5.model.TariffType;

import java.util.Objects;

public class FareEstimationService {
    private static final int FARE_FOR_USING_SERVICE = 49;
    private static volatile FareEstimationService INSTANCE;
    private FareEstimationService() {
    }

    public static synchronized FareEstimationService getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new FareEstimationService();
        }

        return INSTANCE;
    }

    public double estimateFare(double travelTimeEstimationInSeconds, TariffType tariffType) {
        return travelTimeEstimationInSeconds / 60 * tariffType.getRatePerMinute() + FARE_FOR_USING_SERVICE;
    }
}
