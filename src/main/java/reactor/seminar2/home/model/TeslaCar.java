package reactor.seminar2.home.model;

import lombok.Value;

public record TeslaCar(int power, int maxSpeed, TeslaModel model) {
}
