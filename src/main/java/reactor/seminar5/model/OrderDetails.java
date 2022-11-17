package reactor.seminar5.model;

import lombok.*;

import java.util.Currency;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    private String id;
    private String userId;
    private String taxiId;
    private double price;
    private double waitingTime;
    private Currency currency;
    private TaxiRoute taxiRoute;
    private TariffType tariffType;
}