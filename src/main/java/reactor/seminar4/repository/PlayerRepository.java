package reactor.seminar4.repository;

import reactor.core.publisher.Flux;
import reactor.seminar4.model.Player;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static reactor.seminar4.model.Names.*;

public class PlayerRepository {
    private static volatile PlayerRepository INSTANCE;

    private List<Player> players;

    private PlayerRepository() {
        initialize();
    }

    public static synchronized PlayerRepository getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new PlayerRepository();
        }

        return INSTANCE;
    }

    public Flux<Player> findAll() {
        return Flux.fromIterable(players);
    }

    private void initialize() {
        Player hikaru = Player.builder()
                .username(HIKARU.getName())
                .ratingBullet(3342)
                .ratingBlitz(3247)
                .ratingRapid(2822)
                .ratingClassicByFIDE(2768)
                .country("USA")
                .isOnline(true)
                .registeredAt(ZonedDateTime.of(2014, 1, 6, 0, 0, 0, 0, ZoneOffset.ofHours(-5)))
                .lastActivenessDate(ZonedDateTime.of(2022, 11, 3, 12, 10, 5, 44, ZoneOffset.ofHours(-5)))
                .build();

        Player magnusCarlsen = Player.builder()
                .username(MAGNUS_CARLSEN.getName())
                .ratingBullet(3260)
                .ratingBlitz(3190)
                .ratingRapid(2810)
                .ratingClassicByFIDE(2864)
                .country("Norway")
                .isOnline(true)
                .registeredAt(ZonedDateTime.of(2010, 1, 5, 0, 0, 0, 0, ZoneOffset.ofHours(1)))
                .lastActivenessDate(ZonedDateTime.of(2022, 10, 29, 0, 0, 0, 0, ZoneOffset.ofHours(1)))
                .build();

        Player firouzja2003 = Player.builder()
                .username(FIROUZJA_2003.getName())
                .ratingBullet(3360)
                .ratingBlitz(3129)
                .ratingRapid(2483)
                .ratingClassicByFIDE(2778)
                .country("France")
                .isOnline(true)
                .registeredAt(ZonedDateTime.of(2018, 8, 26, 0, 0, 0, 0, ZoneOffset.ofHours(1)))
                .lastActivenessDate(ZonedDateTime.of(2022, 11, 1, 0, 0, 0, 0, ZoneOffset.ofHours(1)))
                .build();

        Player danielNaroditsky = Player.builder()
                .username(DANIEL_NARODITSKY.getName())
                .ratingBullet(3304)
                .ratingBlitz(3213)
                .ratingRapid(2594)
                .ratingClassicByFIDE(2646)
                .country("USA")
                .isOnline(true)
                .registeredAt(ZonedDateTime.of(2009, 1, 16, 0, 0, 0, 0, ZoneOffset.ofHours(-4)))
                .lastActivenessDate(ZonedDateTime.of(2022, 8, 26, 0, 0, 0, 0, ZoneOffset.ofHours(-4)))
                .build();

        Player anishGiri = Player.builder()
                .username(ANISH_GIRI.getName())
                .ratingBullet(3206)
                .ratingBlitz(3049)
                .ratingRapid(2796)
                .ratingClassicByFIDE(2760)
                .country("Netherlands")
                .isOnline(true)
                .registeredAt(ZonedDateTime.of(2015, 5, 4, 0, 0, 0, 0, ZoneOffset.ofHours(1)))
                .lastActivenessDate(ZonedDateTime.of(2022, 11, 3, 0, 0, 0, 0, ZoneOffset.ofHours(1)))
                .build();

        Player bigFish1995 = Player.builder()
                .username(BIG_FISH_1995.getName())
                .ratingBullet(2636)
                .ratingBlitz(3041)
                .ratingRapid(2686)
                .ratingClassicByFIDE(2685)
                .country("Russia")
                .isOnline(false)
                .registeredAt(ZonedDateTime.of(2016, 1, 23, 0, 0, 0, 0, ZoneOffset.ofHours(3)))
                .lastActivenessDate(ZonedDateTime.of(2022, 11, 3, 0, 0, 0, 0, ZoneOffset.ofHours(3)))
                .build();

        Player grischuk = Player.builder()
                .username(GRISCHUK.getName())
                .ratingBullet(3029)
                .ratingBlitz(3040)
                .ratingRapid(2653)
                .ratingClassicByFIDE(2745)
                .country("Russia")
                .isOnline(true)
                .registeredAt(ZonedDateTime.of(2016, 2, 23, 0, 0, 0, 0, ZoneOffset.ofHours(3)))
                .lastActivenessDate(ZonedDateTime.of(2022, 11, 3, 0, 0, 0, 0, ZoneOffset.ofHours(3)))
                .build();

        Player fabianoCaruana = Player.builder()
                .username(FABIANO_CARUANA.getName())
                .ratingBullet(3045)
                .ratingBlitz(3030)
                .ratingRapid(2702)
                .ratingClassicByFIDE(2776)
                .country("USA")
                .isOnline(true)
                .registeredAt(ZonedDateTime.of(2013, 5, 17, 0, 0, 0, 0, ZoneOffset.ofHours(-5)))
                .lastActivenessDate(ZonedDateTime.of(2022, 11, 1, 0, 0, 0, 0, ZoneOffset.ofHours(-5)))
                .build();

        Player gmBenjaminBok = Player.builder()
                .username(GM_BENJAMIN_BOK.getName())
                .ratingBullet(2925)
                .ratingBlitz(2915)
                .ratingRapid(2631)
                .ratingClassicByFIDE(2618)
                .country("Netherlands")
                .isOnline(false)
                .registeredAt(ZonedDateTime.of(2010, 9, 10, 0, 0, 0, 0, ZoneOffset.ofHours(1)))
                .lastActivenessDate(ZonedDateTime.of(2022, 11, 3, 0, 0, 0, 0, ZoneOffset.ofHours(1)))
                .build();

        Player lachesisQ = Player.builder()
                .username(LACHESIS_Q.getName())
                .ratingBullet(3097)
                .ratingBlitz(3072)
                .ratingRapid(2787)
                .ratingClassicByFIDE(2792)
                .country("Russia")
                .isOnline(true)
                .registeredAt(ZonedDateTime.of(2016, 5, 30, 0, 0, 0, 0, ZoneOffset.ofHours(3)))
                .lastActivenessDate(ZonedDateTime.of(2022, 11, 1, 0, 0, 0, 0, ZoneOffset.ofHours(3)))
                .build();

        hikaru.setFriends(new ArrayList<>(Arrays.asList(danielNaroditsky, fabianoCaruana, gmBenjaminBok, anishGiri)));
        magnusCarlsen.setFriends(new ArrayList<>(Arrays.asList(fabianoCaruana, lachesisQ, firouzja2003)));
        firouzja2003.setFriends(new ArrayList<>(Arrays.asList(magnusCarlsen)));
        danielNaroditsky.setFriends(new ArrayList<>(Arrays.asList(hikaru, fabianoCaruana, gmBenjaminBok, anishGiri, lachesisQ)));
        anishGiri.setFriends(new ArrayList<>(Arrays.asList(hikaru, danielNaroditsky, gmBenjaminBok)));
        bigFish1995.setFriends(new ArrayList<>(Arrays.asList(grischuk, lachesisQ)));
        grischuk.setFriends(new ArrayList<>(Arrays.asList(bigFish1995, lachesisQ)));
        fabianoCaruana.setFriends(new ArrayList<>(Arrays.asList(hikaru, magnusCarlsen, danielNaroditsky)));
        gmBenjaminBok.setFriends(new ArrayList<>(Arrays.asList(hikaru, anishGiri, danielNaroditsky)));
        lachesisQ.setFriends(new ArrayList<>(Arrays.asList(magnusCarlsen, bigFish1995, grischuk, danielNaroditsky)));

        players = Stream.of(hikaru, magnusCarlsen, firouzja2003, danielNaroditsky, anishGiri, bigFish1995, grischuk,
                fabianoCaruana, gmBenjaminBok, lachesisQ).toList();
    }
}
