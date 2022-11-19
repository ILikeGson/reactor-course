package reactor.seminar5;

import reactor.seminar5.model.Coordinates;
import reactor.seminar5.model.Request;
import reactor.seminar5.model.TariffType;
import reactor.seminar5.service.TaxiOrderService;
import reactor.utils.Subscribers;

import java.util.Currency;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        TaxiOrderService taxiOrderService = TaxiOrderService.getInstance();

        taxiOrderService.trackAverageTaxiSpeed()
                .subscribe(Subscribers.defaultSubscriber());

        Thread.sleep(10000L);

        Request.builder()
                .userId("123")
                .from(new Coordinates(59.936803, 30.313636))
                .to(new Coordinates(59.930954, 30.361046))
                .tariffType(TariffType.ECONOM)
                .currency(Currency.getInstance("840"))
                .build();
    }


    // Найти userId, который потратил наибольшую сумму на такси
    // Посчитать сумму, потраченную на такси для каждого юзера.
}
