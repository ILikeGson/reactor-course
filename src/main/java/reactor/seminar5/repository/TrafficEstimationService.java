package reactor.seminar5.repository;

import org.apache.commons.lang3.RandomUtils;
import reactor.seminar5.model.TrafficJamIndex;

import java.util.Arrays;
import java.util.Objects;

public class TrafficEstimationService {

    private static volatile TrafficEstimationService INSTANCE;

    private TrafficEstimationService() {
    }

    public static synchronized TrafficEstimationService getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new TrafficEstimationService();
        }

        return INSTANCE;
    }

    public TrafficJamIndex estimateCurrentTraffic() {
        return Arrays.stream(TrafficJamIndex.values()).skip(RandomUtils.nextInt(0, 9))
                .findFirst()
                .orElse(TrafficJamIndex.FIVE);
    }
}
