package reactor.seminar5;

import reactor.seminar5.model.Coordinates;
import reactor.seminar5.model.Request;
import reactor.seminar5.model.TariffType;

public class Client {
    public static void main(String[] args) {
        Request.builder()
                .userId("123")
                .from(new Coordinates(59.936803, 30.313636))
                .to(new Coordinates(59.930954, 30.361046))
                .tariffType(TariffType.ECONOM)
                .build();
    }
}
