package reactor.seminar5.service;

import reactor.seminar5.model.Coordinates;

import java.util.Objects;

public class DistanceEstimationService {
    private static final int EARTH_RADIUS = 6371;
    private static volatile DistanceEstimationService INSTANCE;

    private DistanceEstimationService() {
    }

    public static synchronized DistanceEstimationService getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new DistanceEstimationService();
        }

        return INSTANCE;
    }

    public double estimateDistanceInStraightLineInMeters(Coordinates current, Coordinates target) {
        double fi1 = Math.PI / 180 * current.getLatitude();
        double fi2 = Math.PI / 180 * target.getLatitude();
        double intermediate =
                Math.sin(fi1) * Math.sin(fi2) + (Math.cos(fi1) * Math.cos(fi2))
                                * Math.cos((target.getLongitude() - current.getLongitude()) * Math.PI / 180);
        return Math.acos(intermediate) * EARTH_RADIUS;
    }
}
