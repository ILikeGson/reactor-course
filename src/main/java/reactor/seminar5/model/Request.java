package reactor.seminar5.model;

import lombok.Builder;
import lombok.Value;

import java.util.Currency;

@Value
@Builder
public class Request {
    String userId;
    Coordinates to;
    Coordinates from;
    Currency currency;
    TariffType tariffType;
}
