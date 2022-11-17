package reactor.seminar5.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDescription {
    private String gosNumber;
    private CarManufacturer manufacturer;
    private String model;
    private String color;
    private double serviceTermInSeconds;
}
