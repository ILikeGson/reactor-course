package reactor.seminar5.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxiRoute {
    private Coordinates from;
    private Coordinates to;
    private double distanceInMeters;
    private double expectedTravelTimeInSeconds;
}
