package reactor.seminar5.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxiInfo {
    private String taxiId;
    private double rating;
    private boolean isWorking;
    private TariffType tariffType;
    private CarDescription carDescription;
    private String driverName;
    private Set<Review> reviews;
    private Coordinates lastGpsLocation;
}
