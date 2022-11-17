package reactor.seminar5.service;

import reactor.seminar5.model.TrafficJamIndex;

import java.util.Objects;

public class TravelTimeEstimationService {
    public static final int AVERAGE_CAR_SPEED = 60;
    private static final int SECONDS_IN_HOUR = 3600;
    private static final double AVERAGE_DISTANCE_BETWEEN_TRAFFIC_LIGHTS = 0.4;
    private static final double AVERAGE_WAITING_FOR_TRAFFIC_LIGHT_IN_SECONDS = 30;
    private static volatile TravelTimeEstimationService INSTANCE;

    private TravelTimeEstimationService() {
    }

    public static synchronized TravelTimeEstimationService getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new TravelTimeEstimationService();
        }

        return INSTANCE;
    }

    public double estimateTravelTimeInSecondsConsideringTraffic(double distanceInMeters, TrafficJamIndex currentIndex) {
        return distanceInMeters / (AVERAGE_CAR_SPEED - currentIndex.getSpeedImpact()) * SECONDS_IN_HOUR
                + distanceInMeters / AVERAGE_DISTANCE_BETWEEN_TRAFFIC_LIGHTS * AVERAGE_WAITING_FOR_TRAFFIC_LIGHT_IN_SECONDS;
    }
}