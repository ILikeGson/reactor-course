package reactor.seminar5.repository;

import reactor.core.publisher.Flux;
import reactor.seminar5.model.*;

import java.util.*;

public class TaxiInfoRepository {
    private static volatile TaxiInfoRepository INSTANCE;

    private TaxiInfoRepository() {
    }

    public static synchronized TaxiInfoRepository getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new TaxiInfoRepository();
        }

        return INSTANCE;
    }

    public Flux<TaxiInfo> findAll() {
        return Flux.fromIterable(createTaxiInfoList());
    }

    private List<TaxiInfo> createTaxiInfoList() {
        TaxiInfo taxi_1 = TaxiInfo.builder()
                .taxiId("№1")
                .rating(5)
                .driverName("Azbek")
                .reviews(Set.of(
                        Review.builder()
                                .clientName("Ashot")
                                .message("ai molodec, horosho gonut brat krasay4uk")
                                .rating(5)
                                .build())
                )
                .lastGpsLocation(new Coordinates(59.935875,30.321636))
                .carDescription(CarDescription.builder()
                        .gosNumber("XY777Z")
                        .manufacturer(CarManufacturer.KIA)
                        .model("Rio")
                        .color("Red")
                        .serviceTermInSeconds(10000)
                        .build())
                .isWorking(true)
                .tariffType(TariffType.ECONOM)
                .build();

        TaxiInfo taxi_2 = TaxiInfo.builder()
                .taxiId("№2")
                .rating(5)
                .driverName("Daniel from Taxi")
                .reviews(Set.of(
                        Review.builder()
                                .clientName("Silvestr Stalone")
                                .message("Thanks to Daniel it's got possible to arrive to the airport in time")
                                .rating(5)
                                .build(),
                        Review.builder()
                                .clientName("Сommissioner Zheber")
                                .message("Operation \"snow white\" was cancelled because of the Emiliem")
                                .rating(5)
                                .build()
                        )
                )
                .lastGpsLocation(new Coordinates(43.319103, 5.378821))
                .carDescription(CarDescription.builder()
                        .gosNumber("it changes every hour")
                        .manufacturer(CarManufacturer.PEJO)
                        .model("406")
                        .color("White")
                        .serviceTermInSeconds(100002)
                        .build())
                .isWorking(true)
                .tariffType(TariffType.COMFORT_PLUS)
                .build();

        TaxiInfo taxi_3 = TaxiInfo.builder()
                .taxiId("№3")
                .rating(4.5)
                .driverName("Antonio")
                .reviews(Set.of(
                        Review.builder()
                                .clientName("Maria")
                                .message("Ok")
                                .rating(5)
                                .build(),
                        Review.builder()
                                .clientName("Anastasia")
                                .message("Po4tu Ok")
                                .rating(4)
                                .build()
                        )
                )
                .lastGpsLocation(new Coordinates(59.928989, 30.370357))
                .carDescription(CarDescription.builder()
                        .gosNumber("TA321K")
                        .manufacturer(CarManufacturer.LADA)
                        .model("Vesta")
                        .color("Black")
                        .serviceTermInSeconds(1144175)
                        .build())
                .isWorking(true)
                .tariffType(TariffType.ECONOM)
                .build();

        TaxiInfo taxi_4 = TaxiInfo.builder()
                .taxiId("№4")
                .rating(0)
                .driverName("Benya")
                .lastGpsLocation(new Coordinates(59.943780, 30.348601))
                .carDescription(CarDescription.builder()
                        .gosNumber("AM173O")
                        .manufacturer(CarManufacturer.FORD)
                        .model("Focus 3")
                        .color("Green")
                        .serviceTermInSeconds(523245)
                        .build())
                .isWorking(false)
                .tariffType(TariffType.ECONOM)
                .build();

        TaxiInfo taxi_5 = TaxiInfo.builder()
                .taxiId("№5")
                .rating(3)
                .driverName("Alfred")
                .reviews(Set.of(Review.builder().clientName("Sergei").message("edet po vstre4nou").rating(3).build()))
                .lastGpsLocation(new Coordinates(59.926094, 30.317432))
                .carDescription(CarDescription.builder()
                        .gosNumber("EE111E")
                        .manufacturer(CarManufacturer.WOLKSVAGEN)
                        .model("Polo")
                        .color("Black")
                        .serviceTermInSeconds(33245)
                        .build())
                .tariffType(TariffType.ECONOM)
                .build();

        TaxiInfo taxi_6 = TaxiInfo.builder()
                .taxiId("№6")
                .rating(2)
                .driverName("Igor")
                .reviews(Set.of(Review.builder().clientName("Sergei")
                        .message("Prevbicul ckorostb na 1 km/h").rating(2).build()))
                .lastGpsLocation(new Coordinates(59.941704, 30.335370))
                .carDescription(CarDescription.builder()
                        .gosNumber("AB123Y")
                        .manufacturer(CarManufacturer.KIA)
                        .model("Rio")
                        .color("Yellow")
                        .serviceTermInSeconds(211245)
                        .build())
                .isWorking(true)
                .tariffType(TariffType.ECONOM)
                .build();

        TaxiInfo taxi_7 = TaxiInfo.builder()
                .taxiId("№7")
                .rating(4.7)
                .driverName("Gosha")
                .reviews(Set.of(Review.builder().clientName("Sergei").message("OK").rating(5).build(),
                        Review.builder().clientName("Alexei").message("OK").rating(5).build(),
                        Review.builder().clientName("Alexei").message("Po4ti OK").rating(4).build()))
                .lastGpsLocation(new Coordinates(59.924761, 30.327567))
                .carDescription(CarDescription.builder()
                        .gosNumber("AB123Y")
                        .manufacturer(CarManufacturer.SKODA)
                        .model("Rapid")
                        .color("White")
                        .serviceTermInSeconds(191245)
                        .build())
                .isWorking(true)
                .tariffType(TariffType.ECONOM)
                .build();

        TaxiInfo taxi_8 = TaxiInfo.builder()
                .taxiId("№8")
                .rating(0.0)
                .driverName("Gabriel")
                .lastGpsLocation(new Coordinates(59.922223, 30.340107))
                .carDescription(CarDescription.builder()
                        .gosNumber("BA123Y")
                        .manufacturer(CarManufacturer.KIA)
                        .model("Rio")
                        .color("Black")
                        .serviceTermInSeconds(0)
                        .build())
                .isWorking(true)
                .tariffType(TariffType.ECONOM)
                .build();
        return new ArrayList<>(Arrays.asList(taxi_1, taxi_2, taxi_3, taxi_4, taxi_5, taxi_6, taxi_7, taxi_8));
    }
}
