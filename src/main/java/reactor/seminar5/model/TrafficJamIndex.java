package reactor.seminar5.model;

import lombok.Getter;

@Getter
public enum TrafficJamIndex {
    ONE(1, 4),
    TWO(2, 7),
    THREE(3, 12),
    FOUR(4, 17),
    FIVE(5, 23),
    SIX(6, 28),
    SEVEN(7,34),
    EIGHT(8, 40),
    NINE(9, 47),
    TEN(10, 54);

    private int points;
    private int speedImpact;

    TrafficJamIndex(int points, int speedImpact) {
        this.points = points;
        this.speedImpact = speedImpact;
    }
}
