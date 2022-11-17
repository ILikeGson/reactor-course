package reactor.seminar5.model;

import lombok.Getter;

@Getter
public enum TariffType {
    ECONOM(10),
    COMFORT(17),
    COMFORT_PLUS(21),
    BUSINESS(40),
    CHILDISH(13),
    MINI_VAN(40);

    private final double ratePerMinute;

    TariffType(double ratePerMinute) {
        this.ratePerMinute = ratePerMinute;
    }
}
